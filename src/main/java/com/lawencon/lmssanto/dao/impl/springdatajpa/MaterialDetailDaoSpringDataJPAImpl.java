package com.lawencon.lmssanto.dao.impl.springdatajpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.MaterialDetailDao;
import com.lawencon.lmssanto.model.File;
import com.lawencon.lmssanto.model.MaterialDetail;
import com.lawencon.lmssanto.repo.MaterialDetailRepo;

@Repository
@Profile("springdatajpa-query")
public class MaterialDetailDaoSpringDataJPAImpl implements MaterialDetailDao {

	@PersistenceContext
	private EntityManager em;
	private MaterialDetailRepo materialDetailRepo;

	public MaterialDetailDaoSpringDataJPAImpl(MaterialDetailRepo materialDetailRepo) {
		this.materialDetailRepo = materialDetailRepo;
	}

	@Override
	public MaterialDetail insert(MaterialDetail materialDetail)  {
		materialDetailRepo.save(materialDetail);
		return materialDetail;
	}

	@Override
	public List<MaterialDetail> getAll(Long materialId)  {
		final List<MaterialDetail> materialDetails = new ArrayList<>();
		final List<?> materialDetailObjs = materialDetailRepo.getByMaterialId(materialId);

		if (materialDetailObjs.size() > 0) {
			for (Object materialDetailObj : materialDetailObjs) {
				final Object[] materialDetailArr = (Object[]) materialDetailObj;
				final MaterialDetail getMaterialDetail = new MaterialDetail();
				getMaterialDetail.setId(Long.valueOf(materialDetailArr[0].toString()));

				final File file = new File();
				file.setId(Long.valueOf(materialDetailArr[1].toString()));
				file.setExt(materialDetailArr[2].toString());
				file.setFileName(materialDetailArr[3].toString());

				getMaterialDetail.setMaterialFile(file);
				materialDetails.add(getMaterialDetail);
			}
		}

		return materialDetails;
	}

}
