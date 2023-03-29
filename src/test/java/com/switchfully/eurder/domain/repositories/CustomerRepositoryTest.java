package com.switchfully.eurder.domain.repositories;

import com.switchfully.eurder.domain.models.Address;
import com.switchfully.eurder.domain.models.Customer;
import com.switchfully.eurder.exceptionHandling.exceptions.CustomerNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

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

class CustomerRepositoryTest {

    @Test
    void addCustomer_confirmCustomerHasBeenAddedToList() {
        //Given
        CustomerRepository customerRepository = new CustomerRepository();
        Customer customer = new Customer("First", "Last", "mail", new Address("Street", "123", "Testville", "1234"), "0412345678");
        //When
        customerRepository.addCustomer(customer);
        //Then
        Assertions.assertTrue(customerRepository.getAllCustomers().contains(customer));
    }

    @Test
    void getCustomerById_returnsCustomer_WhenIdIsFound() {
        //Given
        CustomerRepository customerRepository = new CustomerRepository();
        Customer customer = new Customer("First", "Last", "mail", new Address("Street", "123", "Testville", "1234"), "0412345678");
        //When
        customerRepository.addCustomer(customer);
        //Then
        Assertions.assertEquals(customer, customerRepository.getCustomerById(customer.getId()));
    }

    @Test
    void getCustomerById_throwsCustomerNotFoundException_WhenIdIsNotFound() {
        CustomerRepository customerRepository = new CustomerRepository();

        Throwable exception = assertThrows(CustomerNotFoundException.class,
                () -> customerRepository.getCustomerById("1"));

        String expectedMessage = "No customer found for id: 1";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}