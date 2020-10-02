package com.mytask.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mytask.app.builder.TaskBuilder;
import com.mytask.app.model.TaskRequest;
import com.mytask.app.repository.TaskRepository;

@RestController
public class TaskController {

	@Autowired
	TaskRepository taskReposiotry;
	
	@Autowired
	TaskBuilder taskBuilder;
	@PostMapping("/addTask")
	public boolean  addUser(@RequestBody TaskRequest task) {
		
		boolean t = taskBuilder.buildTask(task) != null;
		return t;
	}
}
