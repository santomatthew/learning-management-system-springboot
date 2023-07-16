package com.lawencon.lmssanto.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.CommentDao;
import com.lawencon.lmssanto.model.Comment;
import com.lawencon.lmssanto.model.Forum;

@Repository
@Profile("hql-query")
public class CommentDaoHQLImpl implements CommentDao{

	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	public List<Comment> getAll(Long forumId)  {
		final String sql = "SELECT tc FROM Comment tc WHERE tc.forum.id = :forumId ORDER BY tc.createdAt ASC";
		final List<Comment> comments = this.em.createQuery(sql,Comment.class)
				.setParameter("forumId", forumId).getResultList();
		
		return comments;
		
	}

	@Override
	public Comment insert(Comment comment)  {
		this.em.persist(comment);
		return comment;
	}

	
	
	
	
}
