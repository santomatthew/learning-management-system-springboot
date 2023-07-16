package com.lawencon.lmssanto.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.model.StudentAttendance;

@Repository
public interface StudentAttendanceRepo extends JpaRepository<StudentAttendance, Long>{

	Object getByStudentIdAndElearningId (Long studentId ,Long elearningId) ;

	@Query(value="SELECT tsa.id, tsa.elearning_id, tsa.attend_time, tsa.is_approved "
				+ " FROM t_student_attendance tsa "
				+ " INNER JOIN t_user tu ON tsa.student_id = tu.id "
				+ " INNER JOIN t_profile tp ON tu.profile_id = tp.id "
				+ " WHERE tsa.student_id = :studentId "
				+ " AND tsa.elearning_id = :elearningId",nativeQuery = true)
	List<Object> getByElearningId (Long elearningId) ;
	
	StudentAttendance getById(Long id);
	
}
