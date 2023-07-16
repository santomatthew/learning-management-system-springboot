package com.lawencon.lmssanto.dao.impl.springdatajpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.QuestionAnswerDao;
import com.lawencon.lmssanto.model.QuestionAnswer;
import com.lawencon.lmssanto.repo.QuestionAnswerRepo;

@Repository
@Profile("springdatajpa-query")
public class QuestionAnswerDaoSpringDataJPAImpl implements QuestionAnswerDao {

	@PersistenceContext
	private EntityManager em;
	private QuestionAnswerRepo questionAnswerRepo;

	public QuestionAnswerDaoSpringDataJPAImpl(QuestionAnswerRepo questionAnswerRepo) {
		this.questionAnswerRepo = questionAnswerRepo;
	}

	@Override
	public QuestionAnswer getByQuestionIdAndCreatedBy(Long questionId, Long createdBy) {

		final Object questionAnswerObj = questionAnswerRepo.getByQuestionIdAndCreatedBy(questionId, createdBy);

		final Object[] questionAnswerArr = (Object[]) questionAnswerObj;
		QuestionAnswer getQuestionAnswer = null;
		if (questionAnswerArr.length > 0) {
			getQuestionAnswer = new QuestionAnswer();
			getQuestionAnswer.setId(Long.valueOf(questionAnswerArr[0].toString()));

			if (questionAnswerArr[1] != null) {
				getQuestionAnswer.setEssayAnswer(questionAnswerArr[1].toString());
			} else {
				getQuestionAnswer.setEssayAnswer(null);
			}

		}
		return getQuestionAnswer;

	}

	@Override
	public QuestionAnswer insert(QuestionAnswer questionAnswer) {
		questionAnswerRepo.save(questionAnswer);
		return questionAnswer;
	}

}
