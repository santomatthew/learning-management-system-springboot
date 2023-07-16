package com.lawencon.lmssanto.dao.impl;

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
@Profile("native-query")
public class QuestionAnswerDetailDaoImpl implements QuestionAnswerDetailDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public QuestionAnswerDetail insert(QuestionAnswerDetail questionAnswerDetail) {
		em.persist(questionAnswerDetail);

		return questionAnswerDetail;

	}

	@Override
	public List<QuestionAnswerDetail> getAllByQuestionAnswerId(Long questionAnswerId) {
		final String sql = "SELECT tqad.id, tqad.question_answer_file_id ,tf.ext, tf.file_name, tqad.ver "
				+ " FROM t_question_answer_detail tqad "
				+ " INNER JOIN t_file tf ON tqad.question_answer_file_id = tf.id "
				+ " WHERE tqad.question_answer_id = :questionAnswerId";

		final List<?> questionAnswerDetailObjs = this.em.createNativeQuery(sql)
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
				getQuestionAnswerDetail.setVer(Integer.valueOf(questionAnswerDetailArr[4].toString()));
				questionAnswerDetails.add(getQuestionAnswerDetail);

			}
		}

		return questionAnswerDetails;
	}

}
