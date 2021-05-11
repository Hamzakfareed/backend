package com.example.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.modals.Ticket;
import com.example.backend.services.TicketService;

@RestController("/v1/tickets")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	// RETERIVE ALL TICKETS BY ADMIN METHOD
	@GetMapping("/tickets")
	public ResponseEntity<List<Ticket>> retreiveAllTickets() {
		List<Ticket> tickets = ticketService.findAllTickets();

		if (tickets != null) {
			return ResponseEntity.ok(tickets);

		}
		return ResponseEntity.badRequest().build();
	}

}
