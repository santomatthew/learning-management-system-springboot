package com.lawencon.lmssanto.dao.impl.springdatajpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.ClassRoomEnrollDao;
import com.lawencon.lmssanto.model.ClassRoomEnroll;
import com.lawencon.lmssanto.repo.ClassRoomEnrollRepo;

@Repository
@Profile("springdatajpa-query")
public class ClassRoomEnrollDaoSpringDataJPAImpl implements ClassRoomEnrollDao{

	@PersistenceContext
	private EntityManager em;
	private ClassRoomEnrollRepo classRoomEnrollRepo;
	
	
	public ClassRoomEnrollDaoSpringDataJPAImpl(ClassRoomEnrollRepo classRoomEnrollRepo) {
		this.classRoomEnrollRepo = classRoomEnrollRepo;
	}
	
	
	@Override
	public List<ClassRoomEnroll> getAllByStudentId(ClassRoomEnroll classRoomEnroll)  {
		final Long studentId = classRoomEnroll.getStudent().getId();
		final List<ClassRoomEnroll> classRoomEnrolls = classRoomEnrollRepo.getByStudentId(studentId);
		
		return classRoomEnrolls;
	}

	@Override
	public ClassRoomEnroll insert(ClassRoomEnroll classRoomEnroll)  {
		classRoomEnrollRepo.save(classRoomEnroll);

		return classRoomEnroll;
	}

}
