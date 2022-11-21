package com.cybage.ecommerce.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.ecommerce.exceptions.*;
import com.cybage.ecommerce.repositories.*;

import com.cybage.ecommerce.entities.*;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController

//@RequestMapping("/api/")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	// get all users
	@GetMapping("/user")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// create employee rest api
	@PostMapping("/adduser")
	public User createEmployee(@RequestBody User user) {
		return userRepository.save(user);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundExeption("User Not Exists"));
		return ResponseEntity.ok(user);
	}
	// update employee rest api

	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundExeption("User not exist with id :" + id));

		user.setNumber(userDetails.getNumber());
		user.setEmailId(userDetails.getEmailId());
		user.setPassword(userDetails.getPassword());

		User updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}
	// delete employee rest api
		@DeleteMapping("/users/{id}")
		public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
			User user = userRepository.findById(id)
					.orElseThrow(() -> new UserNotFoundExeption("User not exist with id :" + id));
			
			userRepository.delete(user);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
}
