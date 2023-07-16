package com.lawencon.lmssanto.dao.impl.springdatajpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.CommentDao;
import com.lawencon.lmssanto.model.Comment;
import com.lawencon.lmssanto.repo.CommentRepo;

@Repository
@Profile("springdatajpa-query")
public class CommentDaoSpringDataJPAImpl implements CommentDao{

	@PersistenceContext
	private EntityManager em;
	private CommentRepo commentRepo;
	
	
	public CommentDaoSpringDataJPAImpl(CommentRepo commentRepo) {
		this.commentRepo = commentRepo;
	}
	
	@Override
	public List<Comment> getAll(Long forumId)  {
		final List<Comment> comments = commentRepo.getByForumId(forumId);
		return comments;
		
	}

	@Override
	public Comment insert(Comment comment)  {
		commentRepo.save(comment);
		return comment;
	}

	
	
	
	
}
