package com.lawencon.lmssanto.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.MaterialDao;
import com.lawencon.lmssanto.model.Material;

@Repository
@Profile("native-query")
public class MaterialDaoImpl implements MaterialDao{

	@PersistenceContext
	private  EntityManager em;

	@Override
	public Material insert(Material material)  {
		em.persist(material);
		return material;
	}

	@Override
	public List<Material> getAll(Long elearningId)  {
		final String sql = "SELECT * FROM t_material WHERE elearning_id = :elearningId";
		final List<Material> materials = this.em
				.createNativeQuery(sql, Material.class)
				.setParameter("elearningId", elearningId).getResultList();
		
		return materials;
	}

	@Override
	public Material getById(Long id) {
		final Material material = this.em.find(Material.class, id);
		return material;
	}

	

}
