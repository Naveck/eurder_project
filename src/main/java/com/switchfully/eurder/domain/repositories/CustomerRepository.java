package com.switchfully.eurder.domain.repositories;

import com.switchfully.eurder.domain.models.Customer;
import com.switchfully.eurder.exceptionHandling.exceptions.CustomerNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository {

    private final List<Customer> customerDatabase = new ArrayList<>();

    public void addCustomer(Customer newCustomer) {
        customerDatabase.add(newCustomer);
    }

    public List<Customer> getAllCustomers() {
        return List.copyOf(customerDatabase);
    }

    public Customer getCustomerById(String id) {
        return customerDatabase.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException("No customer found for id: " + id));
    }
}
