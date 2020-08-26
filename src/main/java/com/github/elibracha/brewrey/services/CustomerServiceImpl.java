package com.github.elibracha.brewrey.services;

import com.github.elibracha.brewrey.web.dtos.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public List<CustomerDto> getCustomers(long page, long size) {
        return new ArrayList<>();
    }

    @Override
    public CustomerDto getCustomerById(UUID id) {
        return CustomerDto.builder().id(id).build();
    }

    @Override
    public UUID createCustomer(CustomerDto customer) {
        return UUID.randomUUID();
    }

    @Override
    public UUID updateCustomer(UUID id, CustomerDto customer) {
        return id;
    }

    @Override
    public void deleteCustomer(UUID id) {

    }
}
