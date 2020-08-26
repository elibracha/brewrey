package com.github.elibracha.brewrey.services;

import com.github.elibracha.brewrey.web.dtos.CustomerDto;

import java.util.UUID;

public interface CustomerService {

    CustomerDto getCustomerById(UUID id);

    UUID createCustomer(CustomerDto customer);

    UUID updateCustomer(UUID id, CustomerDto customer);

    void deleteCustomer(UUID id);
}
