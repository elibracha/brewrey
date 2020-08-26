package com.github.elibracha.brewrey.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.elibracha.brewrey.web.dtos.BeerDto;
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

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void findAllBeersTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/beer?page=0&size=10"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void findABeerByIdTest() throws Exception {
        String location = this.createSampleEntity();

        this.mockMvc.perform(
                get(location)
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void createBeerTest() throws Exception {
        this.mockMvc.perform(
                post("/api/v1/beer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new BeerDto(null, "Some Beer", "Some Style", 73423942L)))
        ).andDo(print()).andExpect(status().isCreated()).andExpect(header().exists("Location"));
    }

    @Test
    public void updateBeerTest() throws Exception {
        String location = this.createSampleEntity();

        this.mockMvc.perform(
                put(location)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new BeerDto(null, "Some Beer Updated", "Some Style Updated", 73423942L)))
        ).andDo(print()).andExpect(status().isNoContent()).andExpect(header().string("Location", location));
    }

    @Test
    public void deleteBeerTest() throws Exception {
        String location = this.createSampleEntity();

        this.mockMvc.perform(
                delete(location)
        ).andDo(print()).andExpect(status().isAccepted());
    }

    public String createSampleEntity() throws Exception {
        String location = this.mockMvc.perform(post("/api/v1/beer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(new BeerDto(null, "Some Beer", "Some Style", 73423942L)))
        ).andExpect(header().exists("Location")).andReturn().getResponse().getHeader("Location");

        assert location != null;
        return location;
    }
}
