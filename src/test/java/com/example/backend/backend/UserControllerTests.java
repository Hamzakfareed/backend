package com.example.backend.backend;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class UserControllerTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	// TEST CASE FOR SIGNUP AND SIGN IN 
	@Test
	void signUpAndSignInTest() throws Exception {
		
		//SIGNUP TEST
		mockMvc.perform(post("/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(
						"{ \"firstName\": \"Hamza\", \"lastName\": \"Fareed\",\"email\":\"hamzakfareed@gmail.com\",\"password\":\"test#123\",\"userRole\":\"ROLE_USER\" }"
						)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
		
		//SIGNIN TEST
		MvcResult result = mockMvc.perform(post("/signin")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"email\":\"hamzakfareed@gmail.com\",\"password\":\"test#123\" }")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
		String response = result.getResponse().getContentAsString();
		String accessToken = response.substring(10, response.length() - 2);

		// CHECK IF THE TOCKEN IS GENERATED OR NOT
		assertNotNull(accessToken);

	}

	

}
