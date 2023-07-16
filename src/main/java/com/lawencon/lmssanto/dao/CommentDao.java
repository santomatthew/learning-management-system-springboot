package com.lawencon.lmssanto.dao;

import java.util.List;

import com.lawencon.lmssanto.model.Comment;

public interface CommentDao {

	List<Comment> getAll(Long forumId);

	Comment insert(Comment comment);
}
