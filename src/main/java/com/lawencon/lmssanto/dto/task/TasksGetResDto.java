package com.lawencon.lmssanto.dto.task;

import java.time.LocalDateTime;

public class TasksGetResDto {

	private Long id;
	private String taskName;
	private LocalDateTime taskStartTime;
	private LocalDateTime taskEndTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public LocalDateTime getTaskStartTime() {
		return taskStartTime;
	}

	public void setTaskStartTime(LocalDateTime taskStartTime) {
		this.taskStartTime = taskStartTime;
	}

	public LocalDateTime getTaskEndTime() {
		return taskEndTime;
	}

	public void setTaskEndTime(LocalDateTime taskEndTime) {
		this.taskEndTime = taskEndTime;
	}

}
