package com.lawencon.lmssanto.dao.impl.springdatajpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.QuestionFileDao;
import com.lawencon.lmssanto.model.File;
import com.lawencon.lmssanto.model.QuestionFile;
import com.lawencon.lmssanto.repo.QuestionFileRepo;

@Repository
@Profile("springdatajpa-query")
public class QuestionFileDaoSpringDataJPAImpl implements QuestionFileDao {

	@PersistenceContext
	private EntityManager em;
	private QuestionFileRepo questionFileRepo;

	public QuestionFileDaoSpringDataJPAImpl(QuestionFileRepo questionFileRepo) {
		this.questionFileRepo = questionFileRepo;
	}

	@Override
	public QuestionFile insert(QuestionFile questionFile) {
		questionFileRepo.save(questionFile);
		return questionFile;
	}

	@Override
	public List<QuestionFile> getByQuestionId(Long questionId) {

		final List<?> questionObjs = questionFileRepo.getByQuestionId(questionId);

		final List<QuestionFile> questionFiles = new ArrayList<>();

		if (questionObjs.size() > 0) {
			for (Object questionObj : questionObjs) {
				final Object[] questionArr = (Object[]) questionObj;
				final QuestionFile getQuestionFile = new QuestionFile();
				getQuestionFile.setId(Long.valueOf(questionArr[0].toString()));

				final File file = new File();
				file.setId(Long.valueOf(questionArr[1].toString()));
				file.setExt(questionArr[2].toString());
				file.setFileName(questionArr[3].toString());

				getQuestionFile.setQuestionFile(file);

				questionFiles.add(getQuestionFile);
			}
		}

		return questionFiles;
	}

}
