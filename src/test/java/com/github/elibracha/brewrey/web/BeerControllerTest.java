package com.github.elibracha.brewrey.web;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.elibracha.brewrey.web.dtos.BeerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BeerControllerTest {

    private static final UUID STATIC_BEER_ID_1 = UUID.fromString("8419eedf-9e67-4993-8ee4-51014f676f21");
    private static final UUID STATIC_BEER_ID_2 = UUID.fromString("fd229077-101b-4ca0-a1e1-62e5c038f991");

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    String endpointPrefix = "/api/v1/beer/";

    @BeforeEach
    public void setup() {
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    @Test
    public void findAllBeersTest() throws Exception {
        this.mockMvc.perform(get(endpointPrefix)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void findABeerByIdTest() throws Exception {
        this.mockMvc.perform(get(endpointPrefix + STATIC_BEER_ID_1)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void deleteBeerTest() throws Exception {
        this.mockMvc.perform(delete(endpointPrefix + STATIC_BEER_ID_2)).andDo(print()).andExpect(status().isAccepted());
    }

    @Test
    public void updateBeerTest() throws Exception {
        this.mockMvc.perform(put(endpointPrefix + STATIC_BEER_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        BeerDto.builder().beerName("Some Beer Name")
                                .beerStyle("Some Beer Style")
                                .upc("23213123")
                                .price(BigDecimal.valueOf(341L))))
        ).andDo(print()).andExpect(status().isNoContent()).andExpect(header().exists("Location"));
    }

    @Test
    public void createEntityTest() throws Exception {
        this.mockMvc.perform(
                post(endpointPrefix)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                BeerDto.builder()
                                        .beerName("Some Beer Name")
                                        .beerStyle("Some Beer Style")
                                        .upc(String.valueOf(Double.valueOf(Math.random() * Integer.MAX_VALUE).longValue()))
                                        .price(BigDecimal.valueOf(341L))
                        ))
        ).andExpect(status().isCreated()).andExpect(header().exists("Location"));
    }
}
