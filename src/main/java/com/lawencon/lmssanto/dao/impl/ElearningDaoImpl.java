package com.lawencon.lmssanto.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.ElearningDao;
import com.lawencon.lmssanto.model.Elearning;

@Repository
@Profile("native-query")
public class ElearningDaoImpl implements ElearningDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Elearning insert(Elearning elearning) {
		this.em.persist(elearning);
		return elearning;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Elearning> getByClassRoom(Long classRoomId) {

		final String sql = "SELECT * FROM t_elearning te  WHERE te.classroom_id = :classRoomId ";

		final List<Elearning> elearnings = this.em.createNativeQuery(sql, Elearning.class)
				.setParameter("classRoomId", classRoomId).getResultList();

		return elearnings;
	}

	@Override
	public Elearning getById(Long id) {
		final Elearning elearning = this.em.find(Elearning.class, id);
		return elearning;
	}

}
