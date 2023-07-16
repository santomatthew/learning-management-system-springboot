package com.lawencon.lmssanto.dao.impl.hql;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.QuestionAnswerDao;
import com.lawencon.lmssanto.model.QuestionAnswer;

@Repository
@Profile("hql-query")
public class QuestionAnswerDaoHQLImpl implements QuestionAnswerDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public QuestionAnswer getByQuestionIdAndCreatedBy(Long questionId, Long createdBy) {
		final String sql = "SELECT tqa.id, tqa.essayAnswer "
				+ " FROM QuestionAnswer tqa WHERE tqa.question.id = :questionId AND tqa.createdBy = :createdBy";

		final Object questionAnswerObj = this.em.createQuery(sql).setParameter("questionId", questionId)
				.setParameter("createdBy", createdBy).getSingleResult();

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
		em.persist(questionAnswer);
		return questionAnswer;
	}

}
