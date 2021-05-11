package com.example.backend.backend;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class TicketControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	// MOCKING USER AND TEST CASE FOR CREATE USER TICKET
	@Test
	@WithMockUser(username = "hamzakfareed@gmail.com")
	public void createTicket() throws Exception {
		//CREATE USER WITH USER_ROLE
		mockMvc.perform(post("/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(
						"{ \"firstName\": \"admin\", \"lastName\": \"admin\",\"email\":\"admin@gmail.com\",\"password\":\"test#123\",\"userRole\":\"ROLE_USER\" }"
						)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
		//GET USER TOKEN
		MvcResult result = mockMvc.perform(post("/signin")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"email\":\"admin@gmail.com\",\"password\":\"test#123\" }")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
		String response = result.getResponse().getContentAsString();
		String accessToken = response.substring(10, response.length() - 2);
		
		//PASS USER TOKEN WITH REQUEST TO CREATE TICKET
		mockMvc.perform(post("/users/{user_id}/tickets", 1L)
				.header("Authorization", "Bearer "+accessToken)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"message\": \"This is for testing\"}")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}

	// MOCKING USER AND TEST FOR LISTING ALL TICKETS
	@Test
	@WithMockUser(username = "hamzakfareed@gmail.com")
	public void listOfTickets() throws Exception {
		mockMvc.perform(get("/tickets")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
}
