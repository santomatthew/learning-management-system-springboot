package com.lawencon.lmssanto.dao;

import java.util.List;

import com.lawencon.lmssanto.model.Question;

public interface QuestionDao {

	Question insert(Question question);

	List<Question> getByTaskId(Long taskId);

	Question getById(Long questionId);
}
