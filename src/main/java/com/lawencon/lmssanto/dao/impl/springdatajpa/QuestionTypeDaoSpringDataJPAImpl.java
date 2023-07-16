package com.lawencon.lmssanto.dao.impl.springdatajpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.QuestionTypeDao;
import com.lawencon.lmssanto.model.QuestionType;
import com.lawencon.lmssanto.repo.QuestionTypeRepo;

@Repository
@Profile("springdatajpa-query")
public class QuestionTypeDaoSpringDataJPAImpl implements QuestionTypeDao{

	@PersistenceContext
	private EntityManager em;
	private QuestionTypeRepo questionTypeRepo;
	
	public QuestionTypeDaoSpringDataJPAImpl(QuestionTypeRepo questionTypeRepo) {
		this.questionTypeRepo =  questionTypeRepo;
	}
	
	@Override
	public List<QuestionType> getAll()  {
		final List<QuestionType> questionTypes= questionTypeRepo.findAll();
		
		
		return questionTypes;
	}

	@Override
	public QuestionType getById(Long id) {
		final QuestionType questionType = questionTypeRepo.getById(id);
		return questionType;
	}

}
