package com.lawencon.lmssanto.dao;

import java.util.List;

import com.lawencon.lmssanto.model.Task;

public interface TaskDao {

	
	Task insert(Task task);

	List<Task> getByElearningId(Long elearningId);
	
	Task getById(Long id);
}
