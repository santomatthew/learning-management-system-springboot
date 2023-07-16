package com.lawencon.lmssanto.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="t_material_detail")
public class MaterialDetail extends BaseModel {

	@ManyToOne
	@JoinColumn(name="material_id")
	private Material material;
	
	@ManyToOne
	@JoinColumn(name="material_file_id")
	private File materialFile;

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public File getMaterialFile() {
		return materialFile;
	}

	public void setMaterialFile(File materialFile) {
		this.materialFile = materialFile;
	}

	

}
