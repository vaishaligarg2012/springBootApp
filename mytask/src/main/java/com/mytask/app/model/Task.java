package com.mytask.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task {

	private int id;
	private String taskDescription;
	private boolean isApprove;
	private boolean isRejected;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public boolean isApprove() {
		return isApprove;
	}

	public void setApprove(boolean isApprove) {
		this.isApprove = isApprove;
	}

	public boolean isRejected() {
		return isRejected;
	}

	public void setRejected(boolean isRejected) {
		this.isRejected = isRejected;
	}
	
	
}
