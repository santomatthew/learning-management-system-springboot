package com.lawencon.lmssanto.dao.impl.springdatajpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.ClassRoomDao;
import com.lawencon.lmssanto.model.ClassRoom;
import com.lawencon.lmssanto.repo.ClassRoomRepo;

@Repository
@Profile("springdatajpa-query")
public class ClassRoomDaoSpringDataJPAImpl implements ClassRoomDao {

	@PersistenceContext
	private EntityManager em;
	private ClassRoomRepo classRoomRepo;

	public ClassRoomDaoSpringDataJPAImpl(ClassRoomRepo classRoomRepo) {
		this.classRoomRepo = classRoomRepo;
	}

	@Override
	public ClassRoom insert(ClassRoom classRoom) {
		classRoomRepo.save(classRoom);
		return classRoom;
	}

	@Override
	public List<ClassRoom> getByTeacherId(Long teacherId) {
		final List<ClassRoom> classRooms = classRoomRepo.getByTeacherId(teacherId);

		return classRooms;

	}

	@Override
	public List<ClassRoom> getAll() {
		final List<ClassRoom> classRooms = classRoomRepo.findAll();

		return classRooms;

	}

	@Override
	public ClassRoom getById(Long classRoomId) {
		final ClassRoom classRoom = classRoomRepo.getById(classRoomId);
		return classRoom;
	}

	@Override
	public List<ClassRoom> getByClassRoomEnroll(Long studentId) {
		final List<ClassRoom> classRooms = classRoomRepo.getByClassroomEnrollStudentId(studentId);
		return classRooms;
	}

}
