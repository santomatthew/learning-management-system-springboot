package com.lawencon.lmssanto.dto.studentattendance;

import java.time.LocalDateTime;

public class StudentAttsGetResDto {

	private Long id;
	private String fullName;
	private LocalDateTime attendTime;
	private Boolean isApproved;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public LocalDateTime getAttendTime() {
		return attendTime;
	}

	public void setAttendTime(LocalDateTime attendTime) {
		this.attendTime = attendTime;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

}
