package com.lawencon.lmssanto.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="t_question_file")
public class QuestionFile extends BaseModel {

	@ManyToOne
	@JoinColumn(name="question_id")
	private Question question;
	
	@ManyToOne
	@JoinColumn(name="question_file_id")
	private File questionFile;

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public File getQuestionFile() {
		return questionFile;
	}

	public void setQuestionFile(File questionFile) {
		this.questionFile = questionFile;
	}

}
