package com.mytask.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytask.app.model.Task;
import com.mytask.app.repository.TaskRepository;
import java.util.Optional;

@Service
public class TaskService {

	@Autowired
	TaskRepository taskRespository;
	
	public Task addTask(Task task) {
	
		return taskRespository.save(task);
	}
	
	
	public String approveTaskById(int taskId) {
		Task task = new Task();
		Optional<Task> taskOptional = taskRespository.findById(taskId);

        if(taskOptional.get().isApprove()) {
        	return "Already approved";
        }
        
        if(taskOptional.get().isRejected()) {
        	return "Already rejected";
        }
		task.setId(taskId);
		task.setApprove(true);
		task.setTaskDescription(taskOptional.get().getTaskDescription());
		task.setRoles(taskOptional.get().getRoles());
        taskRespository.save(task);
        return "approved success";
	}
	
	public String rejectTaskBy(int taskId) {
		Task task = new Task();
		Optional<Task> taskOptional = taskRespository.findById(taskId);
		if (!taskOptional.isPresent())
			return "Task not found";
        if(!taskOptional.get().isApprove()) {
        	return "Already rejected";
        }
        
        if(taskOptional.get().isApprove()) {
        	return "Already approved";
        }
		task.setId(taskId);
		task.setRejected(true);
		task.setTaskDescription(taskOptional.get().getTaskDescription());
		task.setRoles(taskOptional.get().getRoles());
        taskRespository.save(task);
        return "rejected";
	}


	public java.util.List<Task> getAllTasks() {
		// TODO Auto-generated method stub
		return taskRespository.findAll();
	}
}
