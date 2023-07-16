package com.lawencon.lmssanto.dao.impl.springdatajpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.ElearningDao;
import com.lawencon.lmssanto.model.Elearning;
import com.lawencon.lmssanto.repo.ElearningRepo;

@Repository
@Profile("springdatajpa-query")
public class ElearningDaoSpringDataJPAImpl implements ElearningDao {

	@PersistenceContext
	private EntityManager em;
	private ElearningRepo elearningRepo;

	public ElearningDaoSpringDataJPAImpl(ElearningRepo elearningRepo) {
		this.elearningRepo = elearningRepo;
	}

	@Override
	public Elearning insert(Elearning elearning) {
		elearningRepo.save(elearning);

		return elearning;
	}

	@Override
	public List<Elearning> getByClassRoom(Long classRoomId) {
		final List<Elearning> elearnings = elearningRepo.getByClassroomId(classRoomId);

		return elearnings;
	}

	@Override	
	public Elearning getById(Long id) {
		final Elearning elearning = elearningRepo.getById(id);
		return elearning;
	}

}
