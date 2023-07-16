package com.lawencon.lmssanto.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.model.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {

	List<Comment> getByForumId(Long forumId);
}
