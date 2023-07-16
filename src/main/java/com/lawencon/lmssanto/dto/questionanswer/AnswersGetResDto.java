package com.lawencon.lmssanto.dto.questionanswer;

import java.util.List;

import com.lawencon.lmssanto.dto.file.QuestionFilesResDto;

public class AnswersGetResDto {

	private String questionText;
	private List<QuestionFilesResDto> questionFiles;
	private AnswerGetResDto answer;

	public AnswerGetResDto getAnswer() {
		return answer;
	}

	public void setAnswer(AnswerGetResDto answer) {
		this.answer = answer;
	}

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
}
