package com.bravo.demo.springbootdemo3.repo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Bobby
 * @since 2019/11/3 15:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class PersonRepositoryTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindByFirstName() throws Exception {
        String result = mockMvc.perform(get("/api/persons/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        log.info("result = \n{}", result);

//        String expectedJson = "{\n" +
//                "  \"lastName\" : \"Green\",\n" +
//                "  \"firstName\" : \"Jim\",\n" +
//                "  \"_links\" : {\n" +
//                "    \"self\" : {\n" +
//                "      \"href\" : \"http://localhost/api/persons/1\"\n" +
//                "    },\n" +
//                "    \"person\" : {\n" +
//                "      \"href\" : \"http://localhost/api/persons/1\"\n" +
//                "    }\n" +
//                "  }\n" +
//                "}";
//
//        JSONAssert.assertEquals(expectedJson, result, true);

        String expectedJson = "{\"lastName\" : \"Green\", \"firstName\" : \"Jim\"}";
        JSONAssert.assertEquals(expectedJson, result, false);
    }
}
