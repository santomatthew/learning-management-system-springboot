package com.lawencon.lmssanto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="t_comment")
public class Comment extends BaseModel {

	@ManyToOne
	@JoinColumn(name="classroom_id")
	private Forum forum;

	@Column(name ="user_comment",  nullable = false)
	private String userComment;
	
	@ManyToOne
	@JoinColumn(name="user")
	private User user;

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public String getUserComment() {
		return userComment;
	}

	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
