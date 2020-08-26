package com.github.elibracha.brewrey.models;

import com.github.elibracha.brewrey.web.dtos.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    private UUID id;
    private String name;

    public Customer(CustomerDto customerDto) {
        this.name = customerDto.getName();
    }
}
