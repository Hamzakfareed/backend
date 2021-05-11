package com.example.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.backend.exceptions.DuplicateUserException;
import com.example.backend.modals.User;
import com.example.backend.repositories.UserRepository;

//USER SERVICE CLASS FOR CHECKING DATA BEFORE SENDING TO DATABASE
@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder encoder;

	// LOAD USER BY USER NAME , THIS FUNCTION IS CALLING DURING SIGN IN OPERATION...
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Optional<User> user = userRepository.findByEmail(email);

		if (user.isPresent()) {
			return user.get();
		}
		throw new UsernameNotFoundException("User does not exist" + email);
	}

	// SAVE USER METHOD...
	public User saveUser(User user) throws DuplicateUserException {

		User isUserExist = findByEmail(user.getEmail());

		if (isUserExist != null) {
			throw new DuplicateUserException("User " + user.getEmail() + " already exist");
		}

		user.setPassword(encoder.encode(user.getPassword()));
		User tempUser = userRepository.save(user);

		return tempUser;

	}

	// FIND USER BY HIS EMAIL ADDRESS ...
	public User findByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);

		if (user.isPresent()) {
			return user.get();
		}

		return null;
	}

	// FIND USER BY HIS ID ...
	public User findById(Long userId) {

		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent()) {
			return user.get();
		}

		return null;
	}

	//UPDATE USER FOR FIRST NAME AND LAST NAME ...
	public User updateUser(Long userId, User user) {

		User tempUser = findById(userId);
		tempUser.setFirstName(user.getFirstName());
		tempUser.setLastName(user.getLastName());
		User savedUser = userRepository.save(tempUser);

		if (savedUser != null) {

			return tempUser;
		}

		return null;
	}

}
