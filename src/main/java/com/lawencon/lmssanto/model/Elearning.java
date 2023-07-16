package com.lawencon.lmssanto.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="t_elearning")
public class Elearning extends BaseModel {

	@Column(name ="elearning_name",  nullable = false)
	private String elearningName;
	
	@ManyToOne
	@JoinColumn(name="classroom_id")
	private ClassRoom classroom;
	
	@ManyToOne
	@JoinColumn(name="elearning_photo_id")
	private File elearningPhoto;
	
	@Column(name ="start_date",  nullable = false)
	private LocalDateTime startDate;
	
	@Column(name ="end_date",  nullable = false)
	private LocalDateTime endDate;

	public String getElearningName() {
		return elearningName;
	}

	public void setElearningName(String elearningName) {
		this.elearningName = elearningName;
	}

	public ClassRoom getClassroom() {
		return classroom;
	}

	public void setClassroom(ClassRoom classroom) {
		this.classroom = classroom;
	}

	public File getElearningPhoto() {
		return elearningPhoto;
	}

	public void setElearningPhoto(File elearningPhoto) {
		this.elearningPhoto = elearningPhoto;
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

}
