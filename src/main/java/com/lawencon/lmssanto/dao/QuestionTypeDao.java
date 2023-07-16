package com.lawencon.lmssanto.dao;

import java.util.List;

import com.lawencon.lmssanto.model.QuestionType;

public interface QuestionTypeDao {

	List<QuestionType> getAll();
	
	QuestionType getById(Long id);
	
}
