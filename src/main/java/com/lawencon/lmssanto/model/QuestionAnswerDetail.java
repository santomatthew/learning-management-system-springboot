package com.lawencon.lmssanto.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="t_question_answer_detail")
public class QuestionAnswerDetail extends BaseModel {

	@ManyToOne
	@JoinColumn(name="question_answer_id")
	private QuestionAnswer questionAnswer;
	
	@ManyToOne
	@JoinColumn(name="question_answer_file_id")
	private File questionAnswerFile;

	public QuestionAnswer getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(QuestionAnswer questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	public File getQuestionAnswerFile() {
		return questionAnswerFile;
	}

	public void setQuestionAnswerFile(File questionAnswerFile) {
		this.questionAnswerFile = questionAnswerFile;
	}

}
