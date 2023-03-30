package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.models.Address;
import com.switchfully.eurder.domain.models.Customer;
import com.switchfully.eurder.domain.repositories.CustomerRepository;
import com.switchfully.eurder.exceptionHandling.exceptions.CustomerNotFoundException;
import com.switchfully.eurder.service.dtos.CustomerDTO;
import com.switchfully.eurder.service.mappers.CustomerMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepositoryMock;
    @Mock
    private CustomerMapper customerMapperMock;
    @InjectMocks
    private CustomerService customerService;
    public static final CustomerDTO CUSTOMER_TO_SAVE_DTO = new CustomerDTO("First", "Last", "mail", new Address("Street", "123", "Testville", "1234"), "0412345678");
    public static final Customer CUSTOMER_TO_SAVE = new Customer("First", "Last", "mail", new Address("Street", "123", "Testville", "1234"), "0412345678");

    @Test
    void createCustomer_confirmCustomerHasBeenAddedToList() {
        customerService.createCustomer(CUSTOMER_TO_SAVE_DTO);

        Mockito.verify(customerRepositoryMock).addCustomer(customerMapperMock.toDomain(CUSTOMER_TO_SAVE_DTO));
    }

    @Test
    void getCustomerById_returnsCustomer_whenIdIsFound() {
       when(customerRepositoryMock.getCustomerById(CUSTOMER_TO_SAVE.getId())).thenReturn(CUSTOMER_TO_SAVE);
       when(customerMapperMock.toDTO(CUSTOMER_TO_SAVE)).thenReturn(CUSTOMER_TO_SAVE_DTO);

       CustomerDTO actual = customerService.getCustomerById(CUSTOMER_TO_SAVE.getId());

       Assertions.assertEquals(CUSTOMER_TO_SAVE_DTO, actual);
    }

    @Test
    void getCustomerById_throwsCustomerNotFoundException_whenIdIsNotFound() {
        when(customerRepositoryMock.getCustomerById(anyString())).thenThrow(CustomerNotFoundException.class);

        assertThrows(CustomerNotFoundException.class,
                () -> customerService.getCustomerById("1"));
    }
}