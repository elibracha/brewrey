package com.github.elibracha.brewrey.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.elibracha.brewrey.web.dtos.BeerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    String entityLocation;

    String endpointPrefix = "/api/v1/beer";

    @BeforeEach
    public void createEntityTest() throws Exception {
        String location = this.mockMvc.perform(
                post(endpointPrefix)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new BeerDto(null, "Some Beer",
                                        "Some Style", 73423942L)
                        ))
        ).andExpect(header().exists("Location")).andReturn().getResponse().getHeader("Location");

        assert location != null;
        this.entityLocation = location;
    }

    @Test
    public void findAllBeersTest() throws Exception {
        this.mockMvc.perform(get(endpointPrefix)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void findABeerByIdTest() throws Exception {
        this.mockMvc.perform(get(entityLocation)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void deleteBeerTest() throws Exception {
        this.mockMvc.perform(delete(entityLocation)).andDo(print()).andExpect(status().isAccepted());
    }

    @Test
    public void updateBeerTest() throws Exception {
        this.mockMvc.perform(put(entityLocation)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        new BeerDto(null, "Some Beer Updated",
                                "Some Style Updated", 73423942L)))
        ).andDo(print()).andExpect(status().isNoContent()).andExpect(header().string("Location", entityLocation));
    }
}
