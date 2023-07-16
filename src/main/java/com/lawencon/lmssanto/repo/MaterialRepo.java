package com.lawencon.lmssanto.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.model.Material;

@Repository
public interface MaterialRepo extends JpaRepository<Material, Long> {

	List<Material> getByElearningId(Long elearningId);

	Material getById(Long id);
}
