package com.lawencon.lmssanto.dto.question;

import java.util.List;

import com.lawencon.lmssanto.dto.file.QuestionFilesResDto;

public class QuestionsGetResDto {

	private Long teacherId;
	private Long questionId;
	private String questionText;
	private List<QuestionFilesResDto> questionFiles;

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public List<QuestionFilesResDto> getQuestionFiles() {
		return questionFiles;
	}	

	public void setQuestionFiles(List<QuestionFilesResDto> questionFiles) {
		this.questionFiles = questionFiles;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

}
