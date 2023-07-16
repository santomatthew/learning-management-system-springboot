package com.lawencon.lmssanto.dto.studentattendance;

public class StudentAttUpdateReqDto {

	private Long studentAttendanceId;
	private Boolean isApproved;

	public Long getStudentAttendanceId() {
		return studentAttendanceId;
	}

	public void setStudentAttendanceId(Long studentAttendanceId) {
		this.studentAttendanceId = studentAttendanceId;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

}
