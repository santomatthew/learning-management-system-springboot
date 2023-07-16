package com.lawencon.lmssanto.dao.impl.springdatajpa;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.StudentAttendanceDao;
import com.lawencon.lmssanto.model.Elearning;
import com.lawencon.lmssanto.model.Profile;
import com.lawencon.lmssanto.model.StudentAttendance;
import com.lawencon.lmssanto.model.User;
import com.lawencon.lmssanto.repo.StudentAttendanceRepo;

@Repository
@org.springframework.context.annotation.Profile("springdatajpa-query")
public class StudentAttendanceDaoSpringDataJPAImpl implements StudentAttendanceDao {

	@PersistenceContext
	private EntityManager em;
	private StudentAttendanceRepo studentAttendanceRepo;

	public StudentAttendanceDaoSpringDataJPAImpl(StudentAttendanceRepo studentAttendanceRepo) {
		this.studentAttendanceRepo = studentAttendanceRepo;
	}

	@Override
	public StudentAttendance insert(StudentAttendance studentAttendance) {
		studentAttendanceRepo.save(studentAttendance);
		return studentAttendance;

	}

	@Override
	public StudentAttendance get(Long studentId, Long elearningId) {

		final Object studentAttObj = studentAttendanceRepo.getByStudentIdAndElearningId(studentId, elearningId);

		final Object[] studentAttArr = (Object[]) studentAttObj;
		StudentAttendance getStudentAttendance = null;

		if (studentAttArr.length > 0) {
			getStudentAttendance = new StudentAttendance();
			getStudentAttendance.setId(Long.valueOf(studentAttArr[0].toString()));

			final Elearning elearning = new Elearning();
			elearning.setId(Long.valueOf(studentAttArr[1].toString()));
			getStudentAttendance.setElearning(elearning);

			getStudentAttendance.setAttendTime(Timestamp.valueOf(studentAttArr[2].toString()).toLocalDateTime());
			getStudentAttendance.setIsApproved(Boolean.valueOf(studentAttArr[3].toString()));
		}

		return getStudentAttendance;

	}

	@Override
	public List<StudentAttendance> getAll(Long elearningId) {
		final List<StudentAttendance> studentAttendances = new ArrayList<>();

		final List<?> studentAttObjs = studentAttendanceRepo.getByElearningId(elearningId);

		if (studentAttObjs.size() > 0) {
			for (Object studentAttObj : studentAttObjs) {
				final Object[] studentAttArr = (Object[]) studentAttObj;

				final StudentAttendance getStudentAttendance = new StudentAttendance();
				getStudentAttendance.setId(Long.valueOf(studentAttArr[0].toString()));

				final Elearning elearning = new Elearning();
				elearning.setId(Long.valueOf(studentAttArr[1].toString()));
				getStudentAttendance.setElearning(elearning);

				final User student = new User();
				student.setId(Long.valueOf(studentAttArr[2].toString()));

				final Profile profile = new Profile();
				profile.setFullName(studentAttArr[3].toString());
				student.setProfile(profile);

				getStudentAttendance.setStudent(student);
				getStudentAttendance.setAttendTime(Timestamp.valueOf(studentAttArr[4].toString()).toLocalDateTime());
				getStudentAttendance.setIsApproved(Boolean.valueOf(studentAttArr[5].toString()));

				studentAttendances.add(getStudentAttendance);
			}
		}

		return studentAttendances;
	}

	@Override
	public StudentAttendance getById(Long id) {
		final StudentAttendance studentAttendance =studentAttendanceRepo.getById(id);
		return studentAttendance;
	}

}
