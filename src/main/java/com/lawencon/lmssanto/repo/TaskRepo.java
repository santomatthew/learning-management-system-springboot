package com.lawencon.lmssanto.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.model.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

	List<Task> getByElearningId(Long elearningId) ;
	
	Task getById(Long id);
}
