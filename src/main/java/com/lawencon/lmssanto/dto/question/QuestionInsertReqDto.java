package com.lawencon.lmssanto.dto.question;

import java.util.List;

import com.lawencon.lmssanto.dto.file.FilesInsertReqDto;

public class QuestionInsertReqDto {

	private Long questionTypeId;
	private String questionText;
	private List<FilesInsertReqDto> questionFiles;

	public Long getQuestionTypeId() {
		return questionTypeId;
	}

	public void setQuestionTypeId(Long questionTypeId) {
		this.questionTypeId = questionTypeId;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public List<FilesInsertReqDto> getQuestionFiles() {
		return questionFiles;
	}

	public void setQuestionFiles(List<FilesInsertReqDto> questionFiles) {
		this.questionFiles = questionFiles;
	}

}
