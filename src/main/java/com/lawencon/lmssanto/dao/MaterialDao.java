package com.lawencon.lmssanto.dao;

import java.util.List;

import com.lawencon.lmssanto.model.Material;

public interface MaterialDao {

	Material insert(Material material);

	List<Material> getAll(Long elearningId);

	Material getById(Long id);

	
}
