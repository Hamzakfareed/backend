package com.example.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.modals.Ticket;
import com.example.backend.repositories.TicketRepository;


//TICKET SERVICE CLASS TO CHECKING  DATA BEFORE SENDING TO THE DATABAES
@Service
public class TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	//SAVE TICKET FOR USER ... THIS FUNCTION BELONG TO USER ROLE
	public Ticket saveTicket(Ticket ticket) {
		Ticket tempTicket = ticketRepository.save(ticket);

		if (tempTicket != null) {
			return tempTicket;
		}

		return null;

	}

	//FIND ALL TICKET .. THIS FUNCTION BELONG TO ADMIN ROLE
	public List<Ticket> findAllTickets() {
		List<Ticket> tickets = ticketRepository.findAll();

		if (tickets != null) {
			return tickets;
		}

		return tickets;
	}
}
