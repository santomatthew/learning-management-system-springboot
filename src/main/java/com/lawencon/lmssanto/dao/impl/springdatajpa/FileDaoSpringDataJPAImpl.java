package com.lawencon.lmssanto.dao.impl.springdatajpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.FileDao;
import com.lawencon.lmssanto.model.File;
import com.lawencon.lmssanto.repo.FileRepo;

@Repository
@Profile("springdatajpa-query")
public class FileDaoSpringDataJPAImpl implements FileDao{

	@PersistenceContext
	private EntityManager em;
	private FileRepo fileRepo;
	
	public FileDaoSpringDataJPAImpl(FileRepo fileRepo) {
		this.fileRepo = fileRepo;
		
	}

	
	@Override
	public File insert(File file)  {
		fileRepo.save(file);
		return file;
	}

	@Override
	public File getById(Long id)  {
		final File file = fileRepo.getById(id);
		return file;
	}

}
