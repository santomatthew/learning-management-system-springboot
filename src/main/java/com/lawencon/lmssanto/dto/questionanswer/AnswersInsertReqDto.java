package com.lawencon.lmssanto.dto.questionanswer;

import java.util.List;

public class AnswersInsertReqDto {

	private Long teacherId;
	private Long taskId;
	private List<AnswerInsertReqDto> answers;

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}


	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public List<AnswerInsertReqDto> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerInsertReqDto> answers) {
		this.answers = answers;
	}
	
}
