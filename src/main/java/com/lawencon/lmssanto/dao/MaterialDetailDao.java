package com.lawencon.lmssanto.dao;

import java.util.List;

import com.lawencon.lmssanto.model.MaterialDetail;

public interface MaterialDetailDao {

	MaterialDetail insert(MaterialDetail materialDetail);

	List<MaterialDetail> getAll(Long materialId);
}
