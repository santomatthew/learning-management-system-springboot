package com.lawencon.lmssanto.dto.elearning;

import java.time.LocalDateTime;

public class ElearningInsertReqDto {

	private String elearningName;
	private Long classRoomId;
	private String fileName;
	private String ext;
	private String forumTitle;
	private String forumCode;
	private String forumBody;
	private LocalDateTime startDate;
	private LocalDateTime endDate;

	public String getElearningName() {
		return elearningName;
	}

	public void setElearningName(String elearningName) {
		this.elearningName = elearningName;
	}

	public Long getClassRoomId() {
		return classRoomId;
	}

	public void setClassRoomId(Long classRoomId) {
		this.classRoomId = classRoomId;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

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

}
