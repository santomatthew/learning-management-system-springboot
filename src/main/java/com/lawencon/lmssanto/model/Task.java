package com.lawencon.lmssanto.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_task")
public class Task extends BaseModel {

	@ManyToOne
	@JoinColumn(name = "elearning_id")
	private Elearning elearning;

	@Column(name = "task_name", nullable = false)
	private String taskName;

	@Column(name = "task_start_time", nullable = false)
	private LocalDateTime taskStartTime;

	@Column(name = "task_end_time", nullable = false)
	private LocalDateTime taskEndTime;

	public Elearning getElearning() {
		return elearning;
	}

	public void setElearning(Elearning elearning) {
		this.elearning = elearning;
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

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

}
