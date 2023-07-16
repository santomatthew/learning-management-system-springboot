package com.lawencon.lmssanto.dto.comment;

import java.time.LocalDateTime;

public class CommentsGetResDto {

	private LocalDateTime createdAt;
	private String comment;
	private String commentator;

	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCommentator() {
		return commentator;
	}

	public void setCommentator(String commentator) {
		this.commentator = commentator;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
