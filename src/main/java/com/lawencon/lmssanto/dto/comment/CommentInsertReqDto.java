package com.lawencon.lmssanto.dto.comment;

public class CommentInsertReqDto {

	private Long forumId;
	private String comment;

	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
