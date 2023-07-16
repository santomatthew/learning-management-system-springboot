package com.lawencon.lmssanto.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.QuestionTypeDao;
import com.lawencon.lmssanto.model.QuestionType;

@Repository
@Profile("native-query")
public class QuestionTypeDaoImpl implements QuestionTypeDao{

	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	public List<QuestionType> getAll()  {
		final String sql = "SELECT * FROM t_question_type";
		final List<QuestionType> questionTypes= this.em.createNativeQuery(sql,QuestionType.class).getResultList();
		
		
		return questionTypes;
	}


	@Override
	public QuestionType getById(Long id) {
		final QuestionType questionType = this.em.find(QuestionType.class, id);
		return questionType;
	}

}
