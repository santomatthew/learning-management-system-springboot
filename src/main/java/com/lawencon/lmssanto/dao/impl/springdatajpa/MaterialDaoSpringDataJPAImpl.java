package com.lawencon.lmssanto.dao.impl.springdatajpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.MaterialDao;
import com.lawencon.lmssanto.model.Material;
import com.lawencon.lmssanto.repo.MaterialRepo;

@Repository
@Profile("springdatajpa-query")
public class MaterialDaoSpringDataJPAImpl implements MaterialDao {

	@PersistenceContext
	private EntityManager em;
	private MaterialRepo materialRepo;

	public MaterialDaoSpringDataJPAImpl(MaterialRepo materialRepo) {
		this.materialRepo = materialRepo;
	}

	@Override
	public Material insert(Material material)  {
		materialRepo.save(material);
		return material;
	}

	@Override
	public List<Material> getAll(Long elearningId)  {
		final List<Material> materials = materialRepo.getByElearningId(elearningId);

		return materials;
	}

	@Override
	public Material getById(Long id) {
		final Material material = materialRepo.getById(id);
		return material;
	}

}
