package com.lawencon.lmssanto.dao.impl.hql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.QuestionAnswerDetailDao;
import com.lawencon.lmssanto.model.File;
import com.lawencon.lmssanto.model.QuestionAnswerDetail;

@Repository
@Profile("hql-query")
public class QuestionAnswerDetailDaoHQLImpl implements QuestionAnswerDetailDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public QuestionAnswerDetail insert(QuestionAnswerDetail questionAnswerDetail) {
		em.persist(questionAnswerDetail);

		return questionAnswerDetail;

	}

	@Override
	public List<QuestionAnswerDetail> getAllByQuestionAnswerId(Long questionAnswerId) {
		final String sql = "SELECT tqad.id, tqad.questionAnswerFile.id ,tqad.questionAnswerFile.ext, "
				+ " tqad.questionAnswerFile.fileName " + " FROM QuestionAnswerDetail tqad "
				+ " WHERE tqad.questionAnswer.id = :questionAnswerId";

		final List<?> questionAnswerDetailObjs = this.em.createQuery(sql)
				.setParameter("questionAnswerId", questionAnswerId).getResultList();

		final List<QuestionAnswerDetail> questionAnswerDetails = new ArrayList<>();

		if (questionAnswerDetailObjs.size() > 0) {
			for (Object questionAnswerDetailObj : questionAnswerDetailObjs) {
				final Object[] questionAnswerDetailArr = (Object[]) questionAnswerDetailObj;
				final QuestionAnswerDetail getQuestionAnswerDetail = new QuestionAnswerDetail();
				getQuestionAnswerDetail.setId(Long.valueOf(questionAnswerDetailArr[0].toString()));

				final File file = new File();
				file.setId(Long.valueOf(questionAnswerDetailArr[1].toString()));
				file.setExt(questionAnswerDetailArr[2].toString());
				file.setFileName(questionAnswerDetailArr[3].toString());

				getQuestionAnswerDetail.setQuestionAnswerFile(file);
				questionAnswerDetails.add(getQuestionAnswerDetail);

			}
		}

		return questionAnswerDetails;
	}

}
