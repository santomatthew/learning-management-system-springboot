package com.lawencon.lmssanto.dao.impl.hql;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.FileDao;
import com.lawencon.lmssanto.model.File;

@Repository
@Profile("hql-query")
public class FileDaoHQLImpl implements FileDao{

	@PersistenceContext
	private EntityManager em;
	

	
	@Override
	public File insert(File file)  {
		em.persist(file);
		return file;
	}

	@Override
	public File getById(Long id)  {
		final File file = this.em.find(File.class, id);
		return file;
	}

}
