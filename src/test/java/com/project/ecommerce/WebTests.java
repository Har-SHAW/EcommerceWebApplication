package com.project.ecommerce;

import com.project.ecommerce.controller.HomeController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "shaw")
class WebTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHome() throws Exception {
        this.mockMvc.perform(get("/")).andExpect(status().is(200));
    }
}
