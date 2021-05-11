package com.example.backend.exceptions;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

//HANDLING ALL EXCEPTIONS HERE ....
@ControllerAdvice
public class GlobalExceptionHandler {

	//DUPLICATE USER EXCEPITON HANDLER 
	@ExceptionHandler(value = DuplicateUserException.class)
	public ResponseEntity<Status> duplicateUser(HttpServletRequest request, Exception e) {
		Status status = new Status("User already exist");
		return new ResponseEntity<Status>(status, HttpStatus.BAD_REQUEST);
	}

	//IF ANY VOILATION OF SQL QUERY HAPPEN THEN IT WILL BE HANDLE HERE...
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Status> dataVoilation(HttpServletRequest request, Exception e) {
		Status status = new Status(e.getMessage());
		return new ResponseEntity<Status>(status, HttpStatus.BAD_REQUEST);
	}

	//IF USER IS AUTHENTICATED , UNAUTHORIZED OR FORBIDDEN WILL BE HANDLE HERE
	@ExceptionHandler({ UserAuthenticationException.class })
	public ResponseEntity<String> handleAuthenticationException(UserAuthenticationException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}

	//VALIDATION DATABASE QUERY HANDLER , SUCH AS VALUE LESS THEN MINIMUM SIZE OR ANY NULL VALUE NOT PASSED
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);

		});
		return errors;
	}

	
	//THIS IS WILL HANDLE ANY EXCEPTION WHICH IS NOT HANDLE BY THE ABOVED HANDLERS
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> defaultErrorHandler(HttpServletRequest req, Exception e) {

		return new ResponseEntity<String>("An error occured", HttpStatus.BAD_REQUEST);
	}

}
