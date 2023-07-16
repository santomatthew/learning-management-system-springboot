package com.lawencon.lmssanto.dto.questionanswer;

import java.util.List;

import com.lawencon.lmssanto.dto.file.FilesInsertReqDto;

public class AnswerInsertReqDto {

	private Long questionId;
	private String essayAnswer;
	private List<FilesInsertReqDto> answerFiles;

	public String getEssayAnswer() {
		return essayAnswer;
	}

	public void setEssayAnswer(String essayAnswer) {
		this.essayAnswer = essayAnswer;
	}

	public List<FilesInsertReqDto> getAnswerFiles() {
		return answerFiles;
	}

	public void setAnswerFiles(List<FilesInsertReqDto> answerFiles) {
		this.answerFiles = answerFiles;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

}
