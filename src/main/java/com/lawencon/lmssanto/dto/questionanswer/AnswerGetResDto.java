package com.lawencon.lmssanto.dto.questionanswer;

import java.util.List;

import com.lawencon.lmssanto.dto.file.AnswerFilesGetResDto;

public class AnswerGetResDto {

	private String essayAnswer;
	private List<AnswerFilesGetResDto> answerFiles;

	public String getEssayAnswer() {
		return essayAnswer;
	}

	public void setEssayAnswer(String essayAnswer) {
		this.essayAnswer = essayAnswer;
	}

	public List<AnswerFilesGetResDto> getAnswerFiles() {
		return answerFiles;
	}

	public void setAnswerFiles(List<AnswerFilesGetResDto> answerFiles) {
		this.answerFiles = answerFiles;
	}

}
