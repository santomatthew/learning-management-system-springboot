package com.lawencon.lmssanto.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.model.Profile;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, Long> {

	Profile getById(Long id);

}
