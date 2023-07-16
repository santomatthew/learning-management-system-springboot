package com.lawencon.lmssanto.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.model.Elearning;

@Repository
public interface ElearningRepo extends JpaRepository<Elearning, Long> {

	List<Elearning> getByClassroomId(Long classRoomId) ;
	
	Elearning getById(Long id);
}
		