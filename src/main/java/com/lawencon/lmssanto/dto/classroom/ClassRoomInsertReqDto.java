package com.lawencon.lmssanto.dto.classroom;

public class ClassRoomInsertReqDto {

	private String classroomName;
	private String classroomCode;
	private Long teacherId;
	private String photo;
	private String ext;

	public String getClassroomName() {
		return classroomName;
	}

	public void setClassroomName(String classroomName) {
		this.classroomName = classroomName;
	}

	public String getClassroomCode() {
		return classroomCode;
	}

	public void setClassroomCode(String classroomCode) {
		this.classroomCode = classroomCode;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

}
