package com.lawencon.lmssanto.dao.impl.hql;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.ForumDao;
import com.lawencon.lmssanto.model.Elearning;
import com.lawencon.lmssanto.model.Forum;

@Repository
@Profile("hql-query")
public class ForumDaoHQLImpl implements ForumDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Forum insert(Forum forum) {
		this.em.persist(forum);

		return forum;

	}

	@Override
	public Forum getByElearningId(Long elearningId) {
		final String sql = "SELECT tf.id, tf.forumBody, tf.forumCode, tf.forumTitle, tf.elearning.elearningName "
				+ " FROM Forum tf WHERE tf.elearning.id = :elearningId";

		final Object forumObj = this.em.createQuery(sql).setParameter("elearningId", elearningId).getSingleResult();

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
		final Forum forum = this.em.find(Forum.class, id);
		return forum;
	}

}
