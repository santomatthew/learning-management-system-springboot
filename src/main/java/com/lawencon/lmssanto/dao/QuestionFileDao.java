package com.lawencon.lmssanto.dao;

import java.util.List;

import com.lawencon.lmssanto.model.QuestionFile;

public interface QuestionFileDao {

	QuestionFile insert(QuestionFile questionFile);

	List<QuestionFile> getByQuestionId(Long questionId);
	
	
}
