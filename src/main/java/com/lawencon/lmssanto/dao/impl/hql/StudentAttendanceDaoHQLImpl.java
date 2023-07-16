package com.lawencon.lmssanto.dao.impl.hql;

import java.sql.SQLException;
import java.time.LocalDateTime;
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
@org.springframework.context.annotation.Profile("hql-query")
public class StudentAttendanceDaoHQLImpl implements StudentAttendanceDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public StudentAttendance insert(StudentAttendance studentAttendance) {
		em.persist(studentAttendance);
		return studentAttendance;

	}

	@Override
	public StudentAttendance get(Long studentId, Long elearningId) {

		final String sql = "SELECT tsa.id, tsa.elearning.id, tsa.attendTime, tsa.isApproved "
				+ " FROM StudentAttendance tsa " + " WHERE tsa.student.id = :studentId "
				+ " AND tsa.elearning.id = :elearningId ";

		try {
			final Object studentAttObj = this.em.createQuery(sql).setParameter("studentId", studentId)
					.setParameter("elearningId", elearningId).getSingleResult();

			final Object[] studentAttArr = (Object[]) studentAttObj;
			StudentAttendance getStudentAttendance = null;

			if (studentAttArr.length > 0) {
				getStudentAttendance = new StudentAttendance();
				getStudentAttendance.setId(Long.valueOf(studentAttArr[0].toString()));

				final Elearning elearning = new Elearning();
				elearning.setId(Long.valueOf(studentAttArr[1].toString()));
				getStudentAttendance.setElearning(elearning);

				getStudentAttendance.setAttendTime((LocalDateTime) studentAttArr[2]);
				getStudentAttendance.setIsApproved(Boolean.valueOf(studentAttArr[3].toString()));
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
		final String sql = "SELECT tsa.id, tsa.elearning.id, tsa.student.id , tsa.student.profile.fullName, "
				+ " tsa.createdAt ,tsa.isApproved " + " FROM StudentAttendance tsa "
				+ " WHERE tsa.elearning.id = :elearningId";

		final List<?> studentAttObjs = this.em.createQuery(sql).setParameter("elearningId", elearningId)
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
				getStudentAttendance.setAttendTime((LocalDateTime) studentAttArr[4]);
				getStudentAttendance.setIsApproved(Boolean.valueOf(studentAttArr[5].toString()));

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
