package com.lawencon.lmssanto.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.ClassRoomDao;
import com.lawencon.lmssanto.model.ClassRoom;
import com.lawencon.lmssanto.model.ClassRoomEnroll;

@Repository
@Profile("hql-query")
public class ClassRoomDaoHQLImpl implements ClassRoomDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public ClassRoom insert(ClassRoom classRoom) {
		this.em.persist(classRoom);
		return classRoom;
	}

	@Override
	public List<ClassRoom> getByTeacherId(Long teacherId) {
		final String sql = "SELECT cr FROM ClassRoom cr WHERE cr.teacher.id = :teacherId";

		final List<ClassRoom> classRooms = this.em.createQuery(sql, ClassRoom.class)
				.setParameter("teacherId", teacherId).getResultList();

		return classRooms;

	}

	@Override
	public List<ClassRoom> getAll() {
		final String sql = "SELECT cr FROM ClassRoom cr";
		final List<ClassRoom> classRooms = this.em.createQuery(sql, ClassRoom.class).getResultList();

		return classRooms;

	}

	@Override
	public ClassRoom getById(Long classRoomId) {
		final ClassRoom classRoom = this.em.find(ClassRoom.class, classRoomId);
		return classRoom;
	}

	@Override
	public List<ClassRoom> getByClassRoomEnroll(Long studentId) {
		final String sql = "SELECT tc From ClassRoom tc "
				+ " INNER JOIN ClassRoomEnroll tce ON tce.classroom.id = tc.id "
				+ " WHERE tce.student.id = :studentId ";

		final List<ClassRoom> classRooms = this.em.createQuery(sql, ClassRoom.class)
				.setParameter("studentId", studentId).getResultList();

		return classRooms;
	}

}
