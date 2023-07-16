package com.lawencon.lmssanto.dao;

import com.lawencon.lmssanto.model.Profile;

public interface ProfileDao {

	Profile insert(Profile profile);

	Profile getById(Long id);
	
}
	