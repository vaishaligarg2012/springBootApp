package com.mytask.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytask.app.builder.TaskBuilder;
import com.mytask.app.model.Task;
import com.mytask.app.model.TaskRequest;
import com.mytask.app.repository.TaskRepository;
import com.mytask.app.service.TaskService;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

	@Autowired
	TaskRepository taskReposiotry;
	
	@Autowired
	TaskService taskService;
		
	@Autowired
	TaskBuilder taskBuilder;
	@PostMapping("/addTask")
	public Task  addUser(@RequestBody TaskRequest task) {
		return taskBuilder.buildTask(task);
	}
	
	@PutMapping("/approveTask/{id}")
	public String approveTask(@PathVariable int id) {
		String isApprove = taskService.approveTaskById(id);
		return isApprove;
	}
	
	@PutMapping("/rejectTask")
	public String rejectTask(@PathVariable int id) {
		String isRejected = taskService.rejectTaskBy(id);
		return isRejected;
	}
	
	@GetMapping("/getAllTask")
	public List<Task> getTask(){
		return taskService.getAllTasks();
	}
}
