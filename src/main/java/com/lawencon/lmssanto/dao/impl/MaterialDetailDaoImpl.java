package com.lawencon.lmssanto.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.MaterialDetailDao;
import com.lawencon.lmssanto.model.File;
import com.lawencon.lmssanto.model.MaterialDetail;

@Repository
@Profile("native-query")
public class MaterialDetailDaoImpl implements MaterialDetailDao{

	@PersistenceContext
	private EntityManager em;
	

	@Override
	public MaterialDetail insert (MaterialDetail materialDetail)  {
		em.persist(materialDetail);
		return materialDetail;
	}


	@Override
	public List<MaterialDetail> getAll(Long materialId)  {
		final List<MaterialDetail> materialDetails = new ArrayList<>();
		final String sql = "SELECT tmd.id, tmd.material_file_id, tf.ext, tf.file_name tmd.ver FROM "
				+ " t_material_detail tmd INNER JOIN t_file tf ON tf.id = tmd.material_file_id "
				+ " WHERE tmd.material_id = :materialId";
		final List<?> materialDetailObjs = this.em.createNativeQuery(sql).setParameter("materialId", materialId)
				.getResultList();
		
		if(materialDetailObjs.size()>0) {
			for(Object materialDetailObj:materialDetailObjs) {
				final Object[] materialDetailArr = (Object[])materialDetailObj;
				final MaterialDetail getMaterialDetail = new MaterialDetail();
				getMaterialDetail.setId(Long.valueOf(materialDetailArr[0].toString()));
				
				final File file = new File();
				file.setId(Long.valueOf(materialDetailArr[1].toString()));
				file.setExt(materialDetailArr[2].toString());
				file.setFileName(materialDetailArr[3].toString());
				
				getMaterialDetail.setMaterialFile(file);
				getMaterialDetail.setVer(Integer.valueOf(materialDetailArr[4].toString()));
				materialDetails.add(getMaterialDetail);
			
			}
		}
		
		return materialDetails;
	}

}
