package com.lawencon.lmssanto.dto.forum;

import java.util.List;

import com.lawencon.lmssanto.dto.comment.CommentsGetResDto;

public class ForumGetResDto {

	private Long id;
	private String forumBody;
	private String forumCode;
	private String forumTitle;
	private List<CommentsGetResDto> comments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getForumBody() {
		return forumBody;
	}

	public void setForumBody(String forumBody) {
		this.forumBody = forumBody;
	}

	public String getForumCode() {
		return forumCode;
	}

	public void setForumCode(String forumCode) {
		this.forumCode = forumCode;
	}

	public String getForumTitle() {
		return forumTitle;
	}

	public void setForumTitle(String forumTitle) {
		this.forumTitle = forumTitle;
	}

	public List<CommentsGetResDto> getComments() {
		return comments;
	}

	public void setComments(List<CommentsGetResDto> comments) {
		this.comments = comments;
	}

}
