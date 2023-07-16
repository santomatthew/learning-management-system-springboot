package com.lawencon.lmssanto.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.ClassRoomEnrollDao;
import com.lawencon.lmssanto.model.ClassRoomEnroll;

@Repository
@Profile("hql-query")
public class ClassRoomEnrollDaoHQLImpl implements ClassRoomEnrollDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<ClassRoomEnroll> getAllByStudentId(ClassRoomEnroll classRoomEnroll)  {
		final Long studentId = classRoomEnroll.getStudent().getId();
		final String sql = "SELECT cre FROM ClassRoomEnroll cre WHERE cre.student.id = :studentId";
		final List<ClassRoomEnroll> classRoomEnrolls = this.em.createQuery(sql,ClassRoomEnroll.class)
				.setParameter("studentId", studentId).getResultList();
		
		return classRoomEnrolls;
	}

	@Override
	public ClassRoomEnroll insert(ClassRoomEnroll classRoomEnroll)  {
		this.em.persist(classRoomEnroll);

		return classRoomEnroll;
	}

}
