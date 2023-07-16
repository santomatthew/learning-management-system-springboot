package com.lawencon.lmssanto.dto.material;

import java.util.List;

import com.lawencon.lmssanto.dto.file.FilesInsertReqDto;

public class MaterialsInsertReqDto {

	private Long elearningId;
	private String materialText;
	private List<FilesInsertReqDto> materialFiles;

	public String getMaterialText() {
		return materialText;
	}

	public void setMaterialText(String materialText) {
		this.materialText = materialText;
	}

	public List<FilesInsertReqDto> getMaterialFiles() {
		return materialFiles;
	}

	public void setMaterialFiles(List<FilesInsertReqDto> materialFiles) {
		this.materialFiles = materialFiles;
	}

	public Long getElearningId() {
		return elearningId;
	}

	public void setElearningId(Long elearningId) {
		this.elearningId = elearningId;
	}
}
