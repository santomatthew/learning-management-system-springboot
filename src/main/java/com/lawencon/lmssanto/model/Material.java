package com.lawencon.lmssanto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="t_material")
public class Material extends BaseModel{

	@ManyToOne
	@JoinColumn(name="elearning_id")
	private Elearning elearning;
	
	@Column(name ="material_text",nullable = true)
	private String materialText;

	public Elearning getElearning() {
		return elearning;
	}

	public void setElearning(Elearning elearning) {
		this.elearning = elearning;
	}

	public String getMaterialText() {
		return materialText;
	}

	public void setMaterialText(String materialText) {
		this.materialText = materialText;
	}
	
}
