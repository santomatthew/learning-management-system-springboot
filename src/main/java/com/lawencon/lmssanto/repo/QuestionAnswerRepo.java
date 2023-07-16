package com.lawencon.lmssanto.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.model.QuestionAnswer;

@Repository
public interface QuestionAnswerRepo extends JpaRepository<QuestionAnswer, Long> {

	@Query(value="SELECT tqa.id, tqa.essay_answer "
				+ " FROM t_question_answer tqa WHERE tqa.question_id = :questionId "
				+ " AND tqa.created_by = :createdBy",nativeQuery = true)
	Object getByQuestionIdAndCreatedBy(Long questionId ,Long createdBy);

}
