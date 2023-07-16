package com.lawencon.lmssanto.dto.task;

import java.util.List;

import com.lawencon.lmssanto.dto.question.QuestionInsertReqDto;

public class TaskInsertReqDto {

	private Long elearningId;
	private String taskName;
	private String taskStartTime;
	private String taskEndTime;
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

	public List<QuestionInsertReqDto> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionInsertReqDto> questions) {
		this.questions = questions;
	}

	public String getTaskStartTime() {
		return taskStartTime;
	}

	public void setTaskStartTime(String taskStartTime) {
		this.taskStartTime = taskStartTime;
	}

	public String getTaskEndTime() {
		return taskEndTime;
	}

	public void setTaskEndTime(String taskEndTime) {
		this.taskEndTime = taskEndTime;
	}

}
