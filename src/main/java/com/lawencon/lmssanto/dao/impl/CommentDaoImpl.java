package com.lawencon.lmssanto.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.CommentDao;
import com.lawencon.lmssanto.model.Comment;

@Repository
@Profile("native-query")
public class CommentDaoImpl implements CommentDao{

	@PersistenceContext
	private  EntityManager em;
	
	@Override
	public List<Comment> getAll(Long forumId)  {	
		final String sql = "SELECT * FROM t_comment tc WHERE tc.forum_id = :forumId ORDER BY tc.created_at ASC";
		final List<Comment> comments = this.em.createNativeQuery(sql, Comment.class)
				.setParameter("forumId", forumId).getResultList();
		
		return comments;
		
	}

	@Override
	public Comment insert(Comment comment)  {
		this.em.persist(comment);
		return comment;
	}

	
	
	
	
}
