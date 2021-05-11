package com.example.backend.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.backend.exceptions.DuplicateUserException;
import com.example.backend.exceptions.UserAuthenticationException;
import com.example.backend.jwt.resource.JWTResponse;
import com.example.backend.modals.User;
import com.example.backend.services.UserService;
import com.example.backend.utils.Utils;

@RestController("/v1/authentication")
public class UserAuthenticationController {

	@Value("${jwt.http.request.header}")
	private String tokenHeader;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private Utils utils;

	@Autowired
	private UserService userService;

	// USER SIGNUP METHOD
	@PostMapping("/signup")
	public ResponseEntity<User> createUser(@RequestBody User user) throws DuplicateUserException {
		User tempUser = userService.saveUser(user);
		if(tempUser!=null) {
			return ResponseEntity.ok(tempUser);
		}
		
		return ResponseEntity.badRequest().build();
		
	}

	// USER SIGN IN METHOD
	@PostMapping("/signin")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody User user) throws UserAuthenticationException {

		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		} catch (DisabledException e) {
			throw new UserAuthenticationException("User is disabled" + e.getMessage());
		} catch (BadCredentialsException e) {
			throw new UserAuthenticationException("Invalid credentials" + e.getMessage());
		}
		final UserDetails userDetails = userService.loadUserByUsername(user.getEmail());

		final String token = utils.generateToken(userDetails);

		return ResponseEntity.ok(new JWTResponse(token));
	}
}
