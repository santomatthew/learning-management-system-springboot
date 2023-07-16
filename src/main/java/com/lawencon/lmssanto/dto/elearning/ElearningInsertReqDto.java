package com.lawencon.lmssanto.dto.elearning;

public class ElearningInsertReqDto {

	private String elearningName;
	private Long classRoomId;
	private String fileName;
	private String ext;
	private String forumTitle;
	private String forumCode;
	private String forumBody;
	private String startDate;
	private String endDate;

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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
