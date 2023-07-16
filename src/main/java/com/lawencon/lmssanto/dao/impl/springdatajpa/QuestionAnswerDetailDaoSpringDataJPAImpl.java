package com.lawencon.lmssanto.dao.impl.springdatajpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.QuestionAnswerDetailDao;
import com.lawencon.lmssanto.model.File;
import com.lawencon.lmssanto.model.QuestionAnswerDetail;
import com.lawencon.lmssanto.repo.QuestionAnswerDetailRepo;

@Repository
@Profile("springdatajpa-query")
public class QuestionAnswerDetailDaoSpringDataJPAImpl implements QuestionAnswerDetailDao {

	@PersistenceContext
	private EntityManager em;
	private QuestionAnswerDetailRepo questionAnswerDetailRepo;

	public QuestionAnswerDetailDaoSpringDataJPAImpl(QuestionAnswerDetailRepo questionAnswerDetailRepo) {
		this.questionAnswerDetailRepo = questionAnswerDetailRepo;
	}

	@Override
	public QuestionAnswerDetail insert(QuestionAnswerDetail questionAnswerDetail) {
		questionAnswerDetailRepo.save(questionAnswerDetail);

		return questionAnswerDetail;

	}

	@Override
	public List<QuestionAnswerDetail> getAllByQuestionAnswerId(Long questionAnswerId) {

		final List<?> questionAnswerDetailObjs = questionAnswerDetailRepo.getByQuestionAnswerId(questionAnswerId);

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
