package com.lawencon.lmssanto.dao.impl;

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

@Repository
@org.springframework.context.annotation.Profile("native-query")
public class StudentAttendanceDaoImpl implements StudentAttendanceDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public StudentAttendance insert(StudentAttendance studentAttendance) {
		em.persist(studentAttendance);
		return studentAttendance;

	}

	@Override
	public StudentAttendance get(Long studentId, Long elearningId) {

		final String sql = "SELECT tsa.id, tsa.elearning_id, tsa.attend_time, tsa.is_approved, tsa.ver "
				+ " FROM t_student_attendance tsa INNER JOIN t_user tu ON tsa.student_id = tu.id "
				+ " INNER JOIN t_profile tp ON tu.profile_id = tp.id " + " WHERE tsa.student_id = :studentId "
				+ " AND tsa.elearning_id = :elearningId";

		try {
			final Object studentAttObj = this.em.createNativeQuery(sql).setParameter("studentId", studentId)
					.setParameter("elearningId", elearningId).getSingleResult();

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
				getStudentAttendance.setVer(Integer.valueOf(studentAttArr[4].toString()));
			}

			return getStudentAttendance;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<StudentAttendance> getAll(Long elearningId) {
		final List<StudentAttendance> studentAttendances = new ArrayList<>();
		final String sql = "SELECT tsa.id, tsa.elearning_id, tsa.student_id , tp.full_name,"
				+ " tsa.attend_time ,tsa.is_approved, tsa.ver FROM t_student_attendance tsa "
				+ " INNER JOIN t_user tu ON tsa.student_id = tu.id "
				+ " INNER JOIN t_profile tp ON tu.profile_id = tp.id " + " WHERE tsa.elearning_id = :elearningId";

		final List<?> studentAttObjs = this.em.createNativeQuery(sql).setParameter("elearningId", elearningId)
				.getResultList();

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
				getStudentAttendance.setVer(Integer.valueOf(studentAttArr[6].toString()));
				studentAttendances.add(getStudentAttendance);
			}
		}

		return studentAttendances;
	}

	@Override
	public StudentAttendance getById(Long id) {
		final StudentAttendance studentAttendance = this.em.find(StudentAttendance.class, id);
		return studentAttendance;
	}

}
