package com.example.backend.exceptions;

//CUSTOME STATUS FOR ANY EXCEPTION OR RESPONSE TO THE USER
public class Status {
	
	private String message;
	
	public Status(String message) {
		this.message = message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

}
