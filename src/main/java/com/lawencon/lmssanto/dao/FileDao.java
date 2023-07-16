package com.lawencon.lmssanto.dao;

import com.lawencon.lmssanto.model.File;

public interface FileDao {

	File insert(File file);

	File getById(Long id);
}
