package com.lawencon.lmssanto.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.ProfileDao;
import com.lawencon.lmssanto.model.Profile;

@Repository
@org.springframework.context.annotation.Profile("native-query")
public class ProfileDaoImpl implements ProfileDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Profile insert(Profile profile) {
		em.persist(profile);

		return profile;
	}

	@Override
	public Profile getById(Long id) {
		final Profile profile = this.em.find(Profile.class, id);
		return profile;
	}

}
