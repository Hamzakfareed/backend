package com.example.backend.exceptions;


//IF ANY EXCEPTION WHILE AUTHENTICATING USER
public class UserAuthenticationException extends RuntimeException {
	
	private String message;
	public UserAuthenticationException(String message) {
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
		return "UserAuthenticationException [message=" + message + "]";
	}
	
	
}
