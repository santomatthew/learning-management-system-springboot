package com.lawencon.lmssanto.constant;

public enum QuestionTypes {

	ESSAY("ESSAY", "Essay"), FILE("QFL", "File");

	public final String questionTypeCode;
	public final String questionTypeName;

	private QuestionTypes(String questionTypeCode, String questionTypeName) {
		this.questionTypeCode = questionTypeCode;
		this.questionTypeName = questionTypeName;
	}
}
