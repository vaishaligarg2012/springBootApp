package com.mytask.app.builder;

import org.springframework.stereotype.Component;

import com.mytask.app.model.Task;
import com.mytask.app.model.TaskRequest;

@Component
public class TaskBuilder {
	public Task buildTask(TaskRequest taskReq) {
		Task task = new Task();
	    task.setTaskDescription(taskReq.getTaskDescription());
	    return task;
	}
}
