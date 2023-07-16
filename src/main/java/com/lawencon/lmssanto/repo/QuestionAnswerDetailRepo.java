package com.lawencon.lmssanto.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.model.QuestionAnswerDetail;

@Repository
public interface QuestionAnswerDetailRepo extends JpaRepository<QuestionAnswerDetail, Long> {

	@Query(value="SELECT tqad.id, tqad.question_answer_file_id ,tf.ext, tf.file_name FROM t_question_answer_detail tqad "
				+ "INNER JOIN t_file tf ON tqad.question_answer_file_id = tf.id "
				+ "WHERE tqad.question_answer_id = :questionAnswerId",nativeQuery = true)
	List<QuestionAnswerDetail> getByQuestionAnswerId(Long questionAnswerId);
}
