package com.lawencon.lmssanto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name ="t_file")
public class File extends BaseModel {

	@Column(name ="ext",  nullable = false)
	private String ext;
	
	@Column(name ="file_name",  nullable = false)
	private String fileName;

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
