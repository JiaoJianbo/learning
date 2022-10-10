package com.bravo.demo.springbootdemo7.controller;

import com.bravo.demo.springbootdemo7.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

/**
 * @author Bobby
 * @since 2022/10/9 12:07
 */

@DisplayName("JUnit5WithMockito")
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class HelloControllerMockitoTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HelloService helloService;


    @BeforeEach
    void setup() {
        log.info("------ setup ------");
        Mockito.when(helloService.sayHello(Mockito.any())).thenReturn("Hello " + System.currentTimeMillis());
    }

    @Test
    void testGreeting() throws Exception {
        final String result = mockMvc.perform(MockMvcRequestBuilders.get("/greeting").param("name", "World"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        log.info("result==={}", result);
        Assertions.assertTrue(result.startsWith("Hello"));
    }

}
