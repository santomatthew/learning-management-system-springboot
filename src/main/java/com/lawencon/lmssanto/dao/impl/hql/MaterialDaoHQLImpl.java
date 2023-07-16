package com.lawencon.lmssanto.dao.impl.hql;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.MaterialDao;
import com.lawencon.lmssanto.model.Material;

@Repository
@Profile("hql-query")
public class MaterialDaoHQLImpl implements MaterialDao{

	@PersistenceContext
	private EntityManager em;

	@Override
	public Material insert(Material material)  {
		em.persist(material);
		return material;
	}

	@Override
	public List<Material> getAll(Long elearningId)  {
		final String sql = "SELECT m FROM Material m WHERE m.elearning.id = :elearningId";
		final List<Material> materials = this.em.createQuery(sql, Material.class)
				.setParameter("elearningId", elearningId).getResultList();
		
		return materials;
	}

	@Override
	public Material getById(Long id) {
		final Material material = this.em.find(Material.class, id);
		return material;
	}

}
