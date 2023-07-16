package com.lawencon.lmssanto.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.model.ClassRoomEnroll;

@Repository
public interface ClassRoomEnrollRepo extends JpaRepository<ClassRoomEnroll, Long>{

	List<ClassRoomEnroll> getByStudentId(Long studentId) ;

}
