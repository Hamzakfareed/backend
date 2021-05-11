package com.example.backend.modals;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tickets")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne
	@NotNull(message = "{ticket.user.notNull.message}")
	private User user;
	@NotNull(message = "{ticket.message.size.message}")
	private String message;

	public Ticket() {

	}

	public Ticket(User user, String message) {
		this.user = user;
		this.message = message;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", user=" + user + ", message=" + message + "]";
	}

}
