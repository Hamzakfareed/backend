package com.example.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.modals.Ticket;
import com.example.backend.modals.User;
import com.example.backend.services.TicketService;
import com.example.backend.services.UserService;

@RestController("/v1/user_tickets")
public class UserTicketsController {

	@Autowired
	private UserService userService;

	@Autowired
	private TicketService ticketService;

	// CREATE USER TICKET
	@PostMapping("/users/{user_id}/tickets")
	public ResponseEntity<Ticket> createTicket(@PathVariable(name = "user_id") Long userId, @RequestBody Ticket ticket) {

		User user = userService.findById(userId);
		if (user != null) {
			ticket.setUser(user);
			Ticket savedTicket = ticketService.saveTicket(ticket);

			if (savedTicket != null) {
				return ResponseEntity.ok(savedTicket);
			}
		}

		return ResponseEntity.badRequest().build();
	}
}
