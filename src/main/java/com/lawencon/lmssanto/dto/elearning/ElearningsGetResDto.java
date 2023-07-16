package com.lawencon.lmssanto.dto.elearning;

import java.time.LocalDateTime;

public class ElearningsGetResDto {

	private Long id;
	private LocalDateTime startDate;
	private String elearningName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getElearningName() {
		return elearningName;
	}

	public void setElearningName(String elearningName) {
		this.elearningName = elearningName;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

}
