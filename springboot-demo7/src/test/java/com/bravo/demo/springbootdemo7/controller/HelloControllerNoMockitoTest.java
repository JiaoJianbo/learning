package com.bravo.demo.springbootdemo7.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;


/**
 * @author Bobby
 * @since 2022/10/9 11:41
 */
@DisplayName("JUnit5WithoutMockito")
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerNoMockitoTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGreeting() throws Exception {
        final String result = mockMvc.perform(MockMvcRequestBuilders.get("/greeting").param("name", "World"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        Assertions.assertEquals("Hello World", result);
    }

}
