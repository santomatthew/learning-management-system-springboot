package com.lawencon.lmssanto.dao;

import com.lawencon.lmssanto.model.QuestionAnswer;

public interface QuestionAnswerDao {

	QuestionAnswer getByQuestionIdAndCreatedBy(Long questionId,Long createdBy) ;
	QuestionAnswer insert(QuestionAnswer questionAnswer) ;
	
}
