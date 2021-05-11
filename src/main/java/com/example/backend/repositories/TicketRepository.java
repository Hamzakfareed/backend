package com.example.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.modals.Ticket;

//REPOSITORY CLASS WHICH COMMUNICATE WITH TICKETS TABLE IN  DATABASE 
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{

}
