package com.lawencon.lmssanto.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.model.ClassRoom;

@Repository
public interface ClassRoomRepo extends JpaRepository<ClassRoom, Long> {

	List<ClassRoom> getByTeacherId(Long teacherId);

	ClassRoom getById(Long classRoomId);

	@Query(value="SELECT * From t_classroom tc "
				+ " INNER JOIN t_classroom_enroll tce ON tce.classroom_id = tc.id "
				+ " WHERE tce.student_id = :studentId ",nativeQuery = true)
	List<ClassRoom> getByClassroomEnrollStudentId(Long studentId);
}
