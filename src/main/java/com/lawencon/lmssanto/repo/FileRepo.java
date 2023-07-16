package com.lawencon.lmssanto.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.model.File;

@Repository
public interface FileRepo extends JpaRepository<File, Long> {

	File getById(Long id);
}
