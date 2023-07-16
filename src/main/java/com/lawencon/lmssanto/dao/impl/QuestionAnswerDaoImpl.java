package com.lawencon.lmssanto.dao.impl;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.QuestionAnswerDao;
import com.lawencon.lmssanto.model.QuestionAnswer;

@Repository
@Profile("native-query")
public class QuestionAnswerDaoImpl implements QuestionAnswerDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public QuestionAnswer getByQuestionIdAndCreatedBy(Long questionId, Long createdBy) {
		final String sql = "SELECT tqa.id, tqa.essay_answer , tqa.ver"
				+ " FROM t_question_answer tqa WHERE tqa.question_id = :questionId "
				+ " AND tqa.created_by = :createdBy";

		final Object questionAnswerObj = this.em.createNativeQuery(sql).setParameter("questionId", questionId)
				.setParameter("createdBy", createdBy);

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
			
			getQuestionAnswer.setVer(Integer.valueOf(questionAnswerArr[2].toString()));

		}
		return getQuestionAnswer;

	}

	@Override
	public QuestionAnswer insert(QuestionAnswer questionAnswer) {
		em.persist(questionAnswer);
		return questionAnswer;
	}

}
