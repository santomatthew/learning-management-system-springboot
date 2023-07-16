package com.lawencon.lmssanto.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.model.QuestionFile;

@Repository
public interface QuestionFileRepo extends JpaRepository<QuestionFile, Long> {
	@Query(value="SELECT tqf.id, tqf.question_file_id, tf.ext, tf.file_name FROM t_question_file tqf "
				+ "INNER JOIN t_file tf ON tqf.question_file_id = tf.id "
				+ "WHERE tqf.question_id= :questionId ",nativeQuery = true)
	List<QuestionFile> getByQuestionId(Long questionId);
}
