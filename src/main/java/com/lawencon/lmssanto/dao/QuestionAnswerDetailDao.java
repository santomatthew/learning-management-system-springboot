package com.lawencon.lmssanto.dao;

import java.util.List;

import com.lawencon.lmssanto.model.QuestionAnswerDetail;

public interface QuestionAnswerDetailDao {

	
	QuestionAnswerDetail insert (QuestionAnswerDetail questionAnswerDetail);
	List<QuestionAnswerDetail> getAllByQuestionAnswerId (Long questionAnswerId);
}
