package com.lawencon.lmssanto.dao.impl.springdatajpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.ForumDao;
import com.lawencon.lmssanto.model.Elearning;
import com.lawencon.lmssanto.model.Forum;
import com.lawencon.lmssanto.repo.ForumRepo;

@Repository
@Profile("springdatajpa-query")
public class ForumDaoSpringDataJPAImpl implements ForumDao {

	@PersistenceContext
	private EntityManager em;
	private ForumRepo forumRepo;

	public ForumDaoSpringDataJPAImpl(ForumRepo forumRepo) {
		this.forumRepo = forumRepo;
	}

	@Override
	public Forum insert(Forum forum) {
		forumRepo.save(forum);

		return forum;

	}

	@Override
	public Forum getByElearningId(Long elearningId) {

		final Object forumObj = forumRepo.getByElearningId(elearningId);

		final Object[] forumArr = (Object[]) forumObj;
		Forum forumDb = null;

		if (forumArr.length > 0) {
			forumDb = new Forum();
			forumDb.setId(Long.valueOf(forumArr[0].toString()));
			forumDb.setForumBody(forumArr[1].toString());
			forumDb.setForumCode(forumArr[2].toString());
			forumDb.setForumTitle(forumArr[3].toString());

			final Elearning elearning = new Elearning();
			elearning.setId(elearningId);
			elearning.setElearningName(forumArr[4].toString());
			forumDb.setElearning(elearning);
		}

		return forumDb;

	}

	@Override
	public Forum getById(Long id) {
		final Forum forum = forumRepo.getById(id);
		return forum;
	}

}
