package com.lawencon.lmssanto.dao.impl.hql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.QuestionFileDao;
import com.lawencon.lmssanto.model.File;
import com.lawencon.lmssanto.model.QuestionFile;

@Repository
@Profile("hql-query")
public class QuestionFileDaoHQLImpl implements QuestionFileDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public QuestionFile insert(QuestionFile questionFile)  {
	em.persist(questionFile);
		return questionFile;
	}

	@Override
	public List<QuestionFile> getByQuestionId(Long questionId)  {
		final String sql = "SELECT tqf.id, tqf.questionFile.id , tqf.questionFile.ext, tqf.questionFile.fileName "
				+ " FROM QuestionFile tqf "
				+ " WHERE tqf.question.id = :questionId ";
		
		final List<?> questionObjs = this.em.createQuery(sql).setParameter("questionId", questionId)
				.getResultList();
		
		final List<QuestionFile> questionFiles= new ArrayList<>();
		
		if(questionObjs.size()>0) {
			for(Object questionObj : questionObjs) {
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
