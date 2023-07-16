package com.lawencon.lmssanto.dao;

import java.util.List;

import com.lawencon.lmssanto.model.StudentAttendance;

public interface StudentAttendanceDao {

	StudentAttendance insert(StudentAttendance studentAttendance);

	StudentAttendance get(Long studentId, Long elearningId);

	List<StudentAttendance> getAll(Long elearningId);

	StudentAttendance getById(Long id);

}
