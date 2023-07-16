package com.lawencon.lmssanto.dto.task;

import java.time.LocalDateTime;
import java.util.List;

import com.lawencon.lmssanto.dto.question.QuestionInsertReqDto;

public class TaskInsertReqDto {

	private Long elearningId;
	private String taskName;
	private LocalDateTime taskStartTime;
	private LocalDateTime taskEndTime;
	private List<QuestionInsertReqDto> questions;

	public Long getElearningId() {
		return elearningId;
	}

	public void setElearningId(Long elearningId) {
		this.elearningId = elearningId;
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

	public List<QuestionInsertReqDto> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionInsertReqDto> questions) {
		this.questions = questions;
	}

}
