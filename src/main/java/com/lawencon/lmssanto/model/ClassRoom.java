package com.lawencon.lmssanto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="t_classroom")
public class ClassRoom extends BaseModel {

	@Column(name ="classroom_name",  nullable = false)
	private String classroomName;
	
	@Column(name ="classroom_code",  nullable = false)
	private String classroomCode;
	
	@ManyToOne
	@JoinColumn(name="teacher_id")
	private User teacher;
	
	@ManyToOne
	@JoinColumn(name="classroom_photo_id")
	private File photo;

	
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

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

}
