package com.lawencon.lmssanto.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.ClassRoomDao;
import com.lawencon.lmssanto.model.ClassRoom;

@Repository
@Profile("native-query")
public class ClassRoomDaoImpl implements ClassRoomDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public ClassRoom insert(ClassRoom classRoom) {
		this.em.persist(classRoom);
		return classRoom;
	}

	@Override
	public List<ClassRoom> getByTeacherId(Long teacherId) {
		final String sql = "SELECT * FROM t_classroom WHERE teacher_id = :teacherId";

		final List<ClassRoom> classRooms = this.em.createNativeQuery(sql, ClassRoom.class)
				.setParameter("teacherId", teacherId).getResultList();

		return classRooms;

	}

	@Override
	public List<ClassRoom> getAll() {
		final String sql = "SELECT * FROM t_classroom tc";
		final List<ClassRoom> classRooms = this.em.createNativeQuery(sql, ClassRoom.class).getResultList();

		return classRooms;

	}

	@Override
	public ClassRoom getById(Long classRoomId) {
		final ClassRoom classRoom = this.em.find(ClassRoom.class, classRoomId);
		return classRoom;
	}

	@Override
	public List<ClassRoom> getByClassRoomEnroll(Long studentId) {
		final String sql = "SELECT * From t_classroom tc "
				+ " INNER JOIN t_classroom_enroll tce ON tce.classroom_id = tc.id "
				+ " WHERE tce.student_id = :studentId ";

		final List<ClassRoom> classRooms = this.em.createNativeQuery(sql, ClassRoom.class)
				.setParameter("studentId", studentId).getResultList();

		return classRooms;
	}

}
