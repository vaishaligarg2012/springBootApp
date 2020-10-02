package com.mytask.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytask.app.model.Task;
import com.mytask.app.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	TaskRepository taskRespository;
	
	public Task addTask(Task task) {
		return taskRespository.save(task);
	}
	
	public boolean approveTask(String taskId) {
		return false;
		
	}
	
	public boolean rejectTask(String taskId) {
		return false;
	}
}
