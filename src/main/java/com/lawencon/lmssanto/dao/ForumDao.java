package com.lawencon.lmssanto.dao;

import com.lawencon.lmssanto.model.Forum;

public interface ForumDao {

	Forum insert(Forum forum);

	Forum getByElearningId(Long elearningId);

	Forum getById(Long id);
}
