package com.lawencon.lmssanto.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.TaskDao;
import com.lawencon.lmssanto.model.Task;

@Repository
@Profile("native-query")
public class TaskDaoImpl implements TaskDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Task insert(Task task) {
		em.persist(task);

		return task;
	}

	@Override
	public List<Task> getByElearningId(Long elearningId) {
		final String sql = "SELECT * FROM t_task WHERE elearning_id = :elearningId";

		final List<Task> tasks = this.em.createNativeQuery(sql, Task.class).setParameter("elearningId", elearningId)
				.getResultList();

		return tasks;
	}

	@Override
	public Task getById(Long id) {
		final Task task = this.em.find(Task.class, id);
		return task;
	}

}
