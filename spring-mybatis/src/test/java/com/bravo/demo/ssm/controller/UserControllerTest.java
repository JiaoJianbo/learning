package com.bravo.demo.ssm.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testWhenQuerySuccess() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/users/")
					.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));

		// 下面使用了静态导入
		String result = mockMvc.perform(get("/users/001")
					.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.username").value("admin"))
				.andReturn().getResponse().getContentAsString();
		System.out.println("result = " + result);
	}
	
	@Test 
	public void testWhenCreateSuccess() throws Exception {
		String content = "{\"username\":\"User3\",\"password\":\"111111\",\"birthday\":685728000000,\"age\":28,\"gender\":\"M\"}";
		// String content = "{\"username\":\"User3\",\"password\":null,\"birthday\":685728000000,\"age\":28,\"gender\":\"M\"}";
		
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
				.andExpect(status().isCreated());
	}

	@Test 
	public void testWhenUpdateSuccess() throws Exception {
		String content = "{\"id\":\"002\",\"username\":\"User3\",\"password\":\"111111\",\"birthday\":685728000000,\"age\":28,\"gender\":\"M\"}";
		// String content = "{\"id\":\"002\",\"username\":\"User3\",\"password\":null,\"birthday\":685728000000,\"age\":28,\"gender\":\"M\"}";
		
		mockMvc.perform(put("/users/002").contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
				.andExpect(status().isOk());

		// Overloaded POST
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("X-HTTP-Method-Override", RequestMethod.PUT.toString());
		mockMvc.perform(post("/users/002").headers(httpHeaders).contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
				.andExpect(status().isOk());
	}
	
	@Test 
	public void testWhenUpdateFailure() throws Exception {
		String content = "{\"id\":\"002\",\"username\":\"User3\",\"password\":null,\"birthday\":685728000000,\"age\":28,\"gender\":\"M\"}";
		
		mockMvc.perform(put("/users/002").contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
				.andExpect(status().is4xxClientError());
	}
}
