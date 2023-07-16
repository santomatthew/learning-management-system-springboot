package com.lawencon.lmssanto.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.ForumDao;
import com.lawencon.lmssanto.model.Elearning;
import com.lawencon.lmssanto.model.Forum;

@Repository
@Profile("native-query")
public class ForumDaoImpl implements ForumDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Forum insert(Forum forum) {
		this.em.persist(forum);

		return forum;

	}

	@Override
	public Forum getByElearningId(Long elearningId) {
		final String sql = "SELECT tf.id, tf.forum_body, tf.forum_code, tf.forum_title, te.elearning_name,tf.ver "
				+ " FROM t_forum tf	INNER JOIN t_elearning te ON tf.elearning_id = te.id "
				+ "	WHERE tf.elearning_id = :elearningId";

		final Object forumObj = this.em.createNativeQuery(sql).setParameter("elearningId", elearningId)
				.getSingleResult();

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
			forumDb.setVer(Integer.valueOf(forumArr[5].toString()));
		}

		return forumDb;

	}

	@Override
	public Forum getById(Long id) {
		final Forum forum = this.em.find(Forum.class, id);
		return forum;
	}

}
