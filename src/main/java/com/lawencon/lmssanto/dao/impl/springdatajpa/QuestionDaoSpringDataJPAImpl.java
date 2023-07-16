package com.lawencon.lmssanto.dao.impl.springdatajpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.QuestionDao;
import com.lawencon.lmssanto.model.Question;
import com.lawencon.lmssanto.model.QuestionType;
import com.lawencon.lmssanto.repo.QuestionRepo;

@Repository
@Profile("springdatajpa-query")
public class QuestionDaoSpringDataJPAImpl implements QuestionDao {

	@PersistenceContext
	private EntityManager em;

	private QuestionRepo questionRepo;

	public QuestionDaoSpringDataJPAImpl(QuestionRepo questionRepo) {
		this.questionRepo = questionRepo;
	}

	@Override
	public Question insert(Question question) {
		questionRepo.save(question);

		return question;

	}

	@Override
	public List<Question> getByTaskId(Long taskId) {
		final List<?> questionObjs = questionRepo.getByTaskId(taskId);

		final List<Question> questions = new ArrayList<>();

		if (questionObjs.size() > 0) {
			for (Object questionObj : questionObjs) {
				final Object[] questionArr = (Object[]) questionObj;
				final Question getQuestion = new Question();
				getQuestion.setId(Long.valueOf(questionArr[0].toString()));

				if (questionArr[1] != null) {

					getQuestion.setQuestion(questionArr[1].toString());
				} else {
					getQuestion.setQuestion(null);
				}

				final QuestionType questionType = new QuestionType();
				questionType.setId(Long.valueOf(questionArr[2].toString()));
				questionType.setQuestionTypeCode(questionArr[3].toString());

				getQuestion.setQuestionType(questionType);
				getQuestion.setVer(Integer.valueOf(questionArr[4].toString()));
				getQuestion.setCreatedBy(Long.valueOf(questionArr[5].toString()));

				questions.add(getQuestion);
			}
		}

		return questions;

	}

	@Override
	public Question getById(Long questionId) {
		final Question question = questionRepo.getById(questionId);
		return question;
	}

}
