package com.github.elibracha.brewrey.web;

import com.github.elibracha.brewrey.web.controllers.BeerController;
import com.github.elibracha.brewrey.web.controllers.CustomerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CustomerController.class)
public class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BeerController beerController;
}
