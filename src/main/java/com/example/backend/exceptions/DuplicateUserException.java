package com.example.backend.exceptions;


//THIS IS EXPECTION WILL BE THROW WHEN USER ALREADY EXIST
public class DuplicateUserException extends Exception {

	private String message;
	public DuplicateUserException(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "DuplicateUserException [message=" + message + "]";
	}
	
	
}
