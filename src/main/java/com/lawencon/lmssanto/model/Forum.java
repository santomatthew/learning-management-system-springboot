package com.lawencon.lmssanto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="t_forum")
public class Forum extends BaseModel {

	@Column(name ="forum_title",  nullable = false)
	private String forumTitle;
	
	@Column(name ="forum_code",  nullable = false)
	private String forumCode;
	
	@Column(name ="forum_body",  nullable = false)
	private String forumBody;
	
	@ManyToOne
	@JoinColumn(name="elearning_id")
	private Elearning elearning;

	public String getForumTitle() {
		return forumTitle;
	}

	public void setForumTitle(String forumTitle) {
		this.forumTitle = forumTitle;
	}

	public String getForumCode() {
		return forumCode;
	}

	public void setForumCode(String forumCode) {
		this.forumCode = forumCode;
	}

	public String getForumBody() {
		return forumBody;
	}

	public void setForumBody(String forumBody) {
		this.forumBody = forumBody;
	}

	public Elearning getElearning() {
		return elearning;
	}

	public void setElearning(Elearning elearning) {
		this.elearning = elearning;
	}

}
