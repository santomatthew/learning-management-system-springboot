package com.lawencon.lmssanto.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.ClassRoomEnrollDao;
import com.lawencon.lmssanto.model.ClassRoomEnroll;

@Repository
@Profile("native-query")
public class ClassRoomEnrollDaoImpl implements ClassRoomEnrollDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<ClassRoomEnroll> getAllByStudentId(ClassRoomEnroll classRoomEnroll) {
		final Long studentId = classRoomEnroll.getStudent().getId();
		final String sql = "SELECT * FROM t_classroom_enroll WHERE student_id = :studentId";
		final List<ClassRoomEnroll> classRoomEnrolls = this.em.createNativeQuery(sql, ClassRoomEnroll.class)
				.setParameter("studentId", studentId).getResultList();

		return classRoomEnrolls;
	}

	@Override
	public ClassRoomEnroll insert(ClassRoomEnroll classRoomEnroll) {
		this.em.persist(classRoomEnroll);

		return classRoomEnroll;
	}

}
