package com.lawencon.lmssanto.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.model.Review;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {

	@Query(value = "SELECT tr.id, tr.created_by ,tr.student_id, tp.full_name ,tr.ver FROM t_review tr "
			+ " INNER JOIN t_user tu ON tu.id = tr.student_id " + "INNER JOIN t_profile tp ON tp.id = tu.profile_id "
			+ " WHERE task_id = :taskId",nativeQuery = true)
	List<Object> getByTaskId(Long taskId);

	Review getById(Long id);
}
