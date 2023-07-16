package com.lawencon.lmssanto.dao.impl;

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
@Profile("native-query")
public class QuestionFileDaoImpl implements QuestionFileDao{
	
	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	public QuestionFile insert(QuestionFile questionFile)  {
	em.persist(questionFile);
		return questionFile;
	}

	@Override
	public List<QuestionFile> getByQuestionId(Long questionId)  {
		final String sql = "SELECT tqf.id, tqf.question_file_id, tf.ext, tf.file_name ,tqf.ver "
				+ " FROM t_question_file tqf "
				+ " INNER JOIN t_file tf ON tqf.question_file_id = tf.id "
				+ " WHERE tqf.question_id= :questionId ";
		
		final List<?> questionObjs = this.em.createNativeQuery(sql).setParameter("questionId", questionId)
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
				getQuestionFile.setVer(Integer.valueOf(questionArr[4].toString()));
				questionFiles.add(getQuestionFile);
			}
		}
		
		
		return questionFiles;
	}

}
