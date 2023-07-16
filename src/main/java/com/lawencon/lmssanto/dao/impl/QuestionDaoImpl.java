package com.lawencon.lmssanto.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.QuestionDao;
import com.lawencon.lmssanto.model.Question;
import com.lawencon.lmssanto.model.QuestionType;

@Repository
@Profile("native-query")
public class QuestionDaoImpl implements QuestionDao {

	private EntityManager em;

	@Override
	public Question insert(Question question) {
		em.persist(question);

		return question;

	}

	@Override
	public List<Question> getByTaskId(Long taskId) {
		final String sql = "SELECT tq.id, tq.question , tq.question_type_id , tqt.question_type_code ,tq.ver "
				+ " FROM t_question tq " + " INNER JOIN t_question_type tqt ON tq.question_type_id = tqt.id "
				+ " WHERE tq.task_id = :taskId";

		final List<?> questionObjs = this.em.createNativeQuery(sql).setParameter("taskId", taskId).getResultList();

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

				questions.add(getQuestion);
			}
		}

		return questions;

	}

	@Override
	public Question getById(Long questionId) {
		final Question question = this.em.find(Question.class, questionId);
		return question;
	}

}
