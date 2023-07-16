package com.lawencon.lmssanto.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.model.MaterialDetail;

@Repository
public interface MaterialDetailRepo extends JpaRepository<MaterialDetail, Long>{
	
	@Query(value="SELECT tmd.id, tmd.material_file_id, tf.ext, tf.file_name tmd FROM "
				+ " t_material_detail tmd INNER JOIN t_file tf ON tf.id = tmd.material_file_id "
				+ " WHERE tmd.material_id = :materialId",nativeQuery = true)
	List<Object> getByMaterialId (Long materialId);
}
