package com.switchfully.eurder.customer.service.mappers;

import com.switchfully.eurder.customer.domain.models.Customer;
import com.switchfully.eurder.customer.service.dtos.CustomerDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerMapper {

    public Customer toDomain(CustomerDTO customerDTO) {
        return new Customer(customerDTO.getFirstName(),
                customerDTO.getLastName(),
                customerDTO.getEmail(),
                customerDTO.getAddress(),
                customerDTO.getPhoneNumber());
    }

    public CustomerDTO toDTO(Customer customer) {
        return new CustomerDTO(customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getPhoneNumber());
    }

    public List<CustomerDTO> toCustomerDTOList(List<Customer> customers) {
        return customers.stream()
                .map(customer -> toDTO(customer))
                .toList();
    }
}
