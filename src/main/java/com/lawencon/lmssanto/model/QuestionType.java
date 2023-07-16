package com.lawencon.lmssanto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_question_type")
public class QuestionType extends BaseModel {

	@Column(name = "question_type_code", nullable = false)
	private String questionTypeCode;

	@Column(name = "question_type_name", nullable = false)
	private String questionTypeName;

	public String getQuestionTypeCode() {
		return questionTypeCode;
	}

	public void setQuestionTypeCode(String questionTypeCode) {
		this.questionTypeCode = questionTypeCode;
	}

	public String getQuestionTypeName() {
		return questionTypeName;
	}

	public void setQuestionTypeName(String questionTypeName) {
		this.questionTypeName = questionTypeName;
	}

}
