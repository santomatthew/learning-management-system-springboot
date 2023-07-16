package com.lawencon.lmssanto.dto.classroom;

public class AllClassRoomGetResDto {

	private Long id;
	private String classRoomName;
	private String classRoomCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClassRoomName() {
		return classRoomName;
	}

	public void setClassRoomName(String classRoomName) {
		this.classRoomName = classRoomName;
	}

	public String getClassRoomCode() {
		return classRoomCode;
	}

	public void setClassRoomCode(String classRoomCode) {
		this.classRoomCode = classRoomCode;
	}

}
