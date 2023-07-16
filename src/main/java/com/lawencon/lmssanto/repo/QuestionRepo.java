package com.lawencon.lmssanto.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.model.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {

	@Query(value="SELECT tq.id, tq.question , tq.question_type_id , tqt.question_type_code, tq.ver, tq.created_by FROM t_question tq "
				+ " INNER JOIN t_question_type tqt ON tq.question_type_id = tqt.id "
				+ " WHERE tq.task_id = :taskId",nativeQuery = true)
	List<Question> getByTaskId(Long taskId);
	
	Question getById(Long id);
	
}
	