package com.mytask.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytask.app.model.User;
import com.mytask.app.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userReposiotry;
	public User addUser(User user) {
		
		return userReposiotry.save(user);
	}
}
