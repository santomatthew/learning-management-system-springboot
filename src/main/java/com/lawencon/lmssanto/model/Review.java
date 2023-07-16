package com.lawencon.lmssanto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_review")
public class Review extends BaseModel {

	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private User teacher;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private User student;

	@Column(name = "grade", nullable = true)
	private Float grade;

	@Column(name = "notes", nullable = true)
	private String notes;

	@ManyToOne
	@JoinColumn(name = "task_id")
	private Task task;

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public Float getGrade() {
		return grade;
	}

	public void setGrade(Float grade) {
		this.grade = grade;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

}
