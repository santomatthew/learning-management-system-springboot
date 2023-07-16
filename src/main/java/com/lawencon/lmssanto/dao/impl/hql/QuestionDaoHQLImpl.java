package com.lawencon.lmssanto.dao.impl.hql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.QuestionDao;
import com.lawencon.lmssanto.model.Question;
import com.lawencon.lmssanto.model.QuestionType;

@Repository
@Profile("hql-query")
public class QuestionDaoHQLImpl implements QuestionDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Question insert(Question question)  {
		em.persist(question);
		
		return question;
		
	}
	
	@Override
	public List<Question> getByTaskId(Long taskId)  {
		final String sql = "SELECT tq.id, tq.question , tq.questionType.id , tq.questionType.questionTypeCode, tq.createdBy "
				+ " FROM Question tq "
				+ " WHERE tq.task.id = :taskId";
		
		final List<?>questionObjs = this.em.createQuery(sql)
					.setParameter("taskId", taskId).getResultList();
		
		final List<Question> questions = new ArrayList<>();
		
		if(questionObjs.size()>0) {
			for(Object questionObj : questionObjs) {
				final Object[] questionArr = (Object[]) questionObj;
				final Question getQuestion = new Question();
				getQuestion.setId(Long.valueOf(questionArr[0].toString()));
					
				if(questionArr[1]!=null){
					
					getQuestion.setQuestion(questionArr[1].toString());
				}
				else {
					getQuestion.setQuestion(null);
				}
				
				final QuestionType questionType = new QuestionType();
				questionType.setId(Long.valueOf(questionArr[2].toString()));
				questionType.setQuestionTypeCode(questionArr[3].toString());
				
				getQuestion.setQuestionType(questionType);
				getQuestion.setCreatedBy(Long.valueOf(questionArr[4].toString()));

				questions.add(getQuestion);
			}
		}
		
		return questions;
		
	}

	@Override
	public Question getById(Long questionId)  {
		final Question question = this.em.find(Question.class, questionId);
		return question;
	}

}
