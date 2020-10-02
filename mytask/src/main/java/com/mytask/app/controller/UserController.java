package com.mytask.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mytask.app.model.User;
import com.mytask.app.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/addUser")
	public void addUser(@RequestBody User user) {
		System.out.println("dd");
	    userService.addUser(user);
	}
}
