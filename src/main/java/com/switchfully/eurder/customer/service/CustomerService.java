package com.switchfully.eurder.customer.service;

import com.switchfully.eurder.customer.service.mappers.CustomerMapper;
import com.switchfully.eurder.customer.domain.repositories.CustomerRepository;
import com.switchfully.eurder.customer.service.dtos.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public void createCustomer(CustomerDTO newCustomer) {
        customerRepository.addCustomer(customerMapper.toDomain(newCustomer));
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerMapper.toDTO(customerRepository.getAllCustomers());
    }

    public CustomerDTO getCustomerById(String id) {
        return customerMapper.toDTO(customerRepository.getCustomerById(id));
    }
}
