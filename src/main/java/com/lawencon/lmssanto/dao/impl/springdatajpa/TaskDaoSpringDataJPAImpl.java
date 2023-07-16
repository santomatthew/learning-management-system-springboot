package com.lawencon.lmssanto.dao.impl.springdatajpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.TaskDao;
import com.lawencon.lmssanto.model.Task;
import com.lawencon.lmssanto.repo.TaskRepo;

@Repository
@Profile("springdatajpa-query")
public class TaskDaoSpringDataJPAImpl implements TaskDao {

	@PersistenceContext
	private EntityManager em;
	private TaskRepo taskRepo;

	public TaskDaoSpringDataJPAImpl(TaskRepo taskRepo) {
		this.taskRepo = taskRepo;
	}

	@Override
	public Task insert(Task task) {
		taskRepo.save(task);

		return task;
	}

	@Override
	public List<Task>  getByElearningId(Long elearningId) {
		final List<Task> tasks = taskRepo.getByElearningId(elearningId);

		return tasks;
	}

	@Override
	public Task getById(Long id) {
		final Task task =taskRepo.getById(id);
		return task;
	}

}
