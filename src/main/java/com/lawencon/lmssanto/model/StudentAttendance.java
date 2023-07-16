package com.lawencon.lmssanto.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="t_student_attendance")
public class StudentAttendance extends BaseModel {

	@ManyToOne
	@JoinColumn(name = "student_id")
	private User student;
	
	@ManyToOne
	@JoinColumn(name = "elearning_id")
	private Elearning elearning;
	
	@Column(name="attend_time", nullable = false)
	private LocalDateTime attendTime;
	
	@Column(name="is_approved", nullable = false)
	private Boolean isApproved;

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public Elearning getElearning() {
		return elearning;
	}

	public void setElearning(Elearning elearning) {
		this.elearning = elearning;
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
