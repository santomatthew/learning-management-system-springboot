package com.lawencon.lmssanto.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="t_classroom_enroll")
public class ClassRoomEnroll extends BaseModel {

	@ManyToOne
	@JoinColumn(name="classroom_id")
	private ClassRoom classroom;
	
	@ManyToOne
	@JoinColumn(name="student_id")
	private User student;

	public ClassRoom getClassroom() {
		return classroom;
	}

	public void setClassroom(ClassRoom classroom) {
		this.classroom = classroom;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

}
