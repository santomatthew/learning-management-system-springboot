package com.lawencon.lmssanto.dao;

import java.util.List;

import com.lawencon.lmssanto.model.Review;

public interface ReviewDao {

	Review insert(Review review);

	List<Review> getByTaskId(Long taskId);

	Review getById(Long id);
}
