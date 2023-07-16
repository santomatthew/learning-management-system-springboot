package com.lawencon.lmssanto.dao.impl.springdatajpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.ProfileDao;
import com.lawencon.lmssanto.model.Profile;
import com.lawencon.lmssanto.repo.ProfileRepo;

@Repository
@org.springframework.context.annotation.Profile("springdatajpa-query")
public class ProfileDaoSpringDataJPAImpl implements ProfileDao{

	@PersistenceContext
	private EntityManager em;
	private ProfileRepo profileRepo;
	
	public ProfileDaoSpringDataJPAImpl(ProfileRepo profileRepo) {
		this.profileRepo = profileRepo;
	}
	
	@Override
	public Profile insert(Profile profile)  {
		profileRepo.save(profile);
		
		return profile;
	}

	

	@Override
	public Profile getById(Long id)  {
		final Profile profile = profileRepo.getById(id);
		return profile;
	}

}
