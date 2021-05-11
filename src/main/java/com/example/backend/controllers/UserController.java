package com.example.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.modals.User;
import com.example.backend.services.UserService;

@RestController("/v1/users")
public class UserController {

	@Autowired
	private UserService userService;

	// UPDATE USER FOR FIRST AND LAST NAME
	@PutMapping("/users/{user_id}")
	public ResponseEntity<User> update(@PathVariable(name = "user_id") Long userId, @RequestBody User user) {

		User updatedUser = userService.updateUser(userId, user);
		if (updatedUser != null) {
			return ResponseEntity.ok(updatedUser);

		}

		return ResponseEntity.badRequest().build();
	}

}
