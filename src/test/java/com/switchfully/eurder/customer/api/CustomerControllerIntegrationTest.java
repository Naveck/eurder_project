package com.switchfully.eurder.customer.api;

import com.switchfully.eurder.customer.domain.models.Address;
import com.switchfully.eurder.customer.domain.models.Customer;
import com.switchfully.eurder.customer.domain.repositories.CustomerRepository;
import com.switchfully.eurder.customer.service.dtos.CustomerDTO;
import com.switchfully.eurder.customer.service.mappers.CustomerMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CustomerControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Test
    void whenThereIsOneCustomerInTheRepository_thenICanRetrieveThisCustomerById() {
        // GIVEN
        Customer newCustomer = new Customer("First", "Last", "mail", new Address("Street", "123", "Testville", "1234"), "0412345678");
        customerRepository.addCustomer(newCustomer);
        CustomerDTO newCustomerDTO = new CustomerDTO("First", "Last", "mail", new Address("Street", "123", "Testville", "1234"), "0412345678");


        // WHEN
        CustomerDTO customer = RestAssured
                .given()
                .contentType(ContentType.JSON)
                //.header(new Header("Authorization", "Basic username:password"))
                //.auth().preemptive().basic("username", "password")
                .log().all()
                .when()
                .port(port)
                .get("/customers/" + newCustomer.getId()) // http://localhost:???/contacts/1
                // THEN
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.OK.value()) // status 200
                .extract()
                .as(CustomerDTO.class); // Get a contact from the system

        assertEquals(newCustomerDTO,customer);
    }

    @Test
    void whenTheRepositoryIsEmpty_thenIReceiveA404WhenRequestingACustomerById() {
        RestAssured
                // GIVEN
                .given()
                .contentType(ContentType.JSON)
                // WHEN
                .when()
                .port(port)
                .get("/customers/1") // http://localhost:???/contacts/100
                // THEN
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void whenIPostACustomer_thenTheRepositoryContainsThisCustomer() {
        Customer newCustomer = new Customer("First", "Last", "mail", new Address("Street", "123", "Testville", "1234"), "0412345678");
        CustomerDTO newCustomerDTO = new CustomerDTO("First", "Last", "mail", new Address("Street", "123", "Testville", "1234"), "0412345678");
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(newCustomerDTO)
                .when()
                .port(port)
                .post("/customers") // http://localhost:???/contacts
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

        assertTrue(customerMapper.toCustomerDTOList(customerRepository.getAllCustomers()).contains(newCustomerDTO));
    }

    //@SpringBootTest
//class CustomerRepositoryTest {
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @Test
//    void addCustomer_confirmCustomerHasBeenAddedToList(){
//        //Given
//        Customer customer = new Customer("First", "Last", "mail", new Address("Street", "123", "Testville", "1234"), "0412345678");
//        //When
//        customerRepository.addCustomer(customer);
//        //Then
//        Assertions.assertTrue(customerRepository.getAllCustomers().contains(customer));
//    }
//}

    @Test
    void whenTheRepositoryContains2Customers_thenICanRetrieveThemViaTheAPI() {
        Customer newCust1 = new Customer("First", "Last", "mail", new Address("Street", "123", "Testville", "1234"), "0412345678");
        Customer newCust2 = new Customer("First2", "Last2", "mail2", new Address("Street2", "123", "Testville", "1234"), "0412345678");
        customerRepository.addCustomer(newCust1);
        customerRepository.addCustomer(newCust2);

        List<CustomerDTO> list = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .port(port)
                .get("/customers") // http://localhost:???/contacts
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .jsonPath()
                .getList(".", CustomerDTO.class);

        Assertions.assertThat(list).containsExactlyInAnyOrder(customerMapper.toDTO(newCust1), customerMapper.toDTO(newCust2));
    }
}