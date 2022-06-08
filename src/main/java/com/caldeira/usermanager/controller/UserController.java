package com.caldeira.usermanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caldeira.usermanager.dto.UserDto;
import com.caldeira.usermanager.model.User;
import com.caldeira.usermanager.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<User>> getUsers() {
		List<User> user = service.getAll();
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id) {
		User user = service.get(id);
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user);
	}
	
	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody UserDto userDto) {
		User user = new User(userDto);
		service.save(user);
		return ResponseEntity.ok(user);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@RequestBody UserDto userDto, @PathVariable(value = "id") Long id) {
		if(id == null || id < 1) {
			return ResponseEntity.badRequest().build();
		}
		User user = new User(userDto);
		user.setId(id);
		service.update(user);
		return ResponseEntity.ok(user);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(value="id") Long id) {
		if(id == null || id < 1) {
			return ResponseEntity.badRequest().build();
		}
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
