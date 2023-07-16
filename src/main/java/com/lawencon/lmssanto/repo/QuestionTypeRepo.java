package com.lawencon.lmssanto.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.model.QuestionType;

@Repository
public interface QuestionTypeRepo extends JpaRepository<QuestionType, Long>{

	QuestionType getById(Long id);
	
}
