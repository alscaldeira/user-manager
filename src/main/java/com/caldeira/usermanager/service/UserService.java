package com.caldeira.usermanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caldeira.usermanager.model.User;
import com.caldeira.usermanager.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public void save(User user) {
		userRepository.save(user);
	}
	
	public User get(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user.isEmpty() ? null : user.get();
	}
	
	public List<User> getAll() {
		return userRepository.findAll();
	}
	
	public void update(User user) {
		userRepository.save(user);
	}
	
	public void delete(Long id) {
		userRepository.deleteById(id);
	}
}
