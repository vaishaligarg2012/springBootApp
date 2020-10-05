package com.mytask.app.builder;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mytask.app.model.Task;
import com.mytask.app.model.TaskRequest;
import com.mytask.app.repository.TaskRepository;

@Component
public class TaskBuilder {
	@Autowired
	TaskRepository taskReposiotry;
	
	public Task buildTask(TaskRequest taskReq) {
		Task task = new Task();
		System.out.println(genrateOtp());
		task.setId(genrateOtp());
	    task.setTaskDescription(taskReq.getTaskDescription());
	    task.setRoles(taskReq.getRoles());
	    taskReposiotry.save(task);
	    return task;
	}
	
	
	public int genrateOtp() {
		String numbers = "123456789";
		Random random = new Random();
		StringBuilder otp = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			otp.append(numbers.charAt(random.nextInt(numbers.length())));
		}
		return Integer.parseInt(otp.toString());
	}

}


