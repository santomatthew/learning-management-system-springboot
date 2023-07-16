package com.lawencon.lmssanto.dto.material;

import java.util.List;

public class MaterialsGetResDto {

	private String materialText;
	private List<MaterialFilesGetResDto> materialFiles;

	public String getMaterialText() {
		return materialText;
	}

	public void setMaterialText(String materialText) {
		this.materialText = materialText;
	}

	public List<MaterialFilesGetResDto> getMaterialFiles() {
		return materialFiles;
	}

	public void setMaterialFiles(List<MaterialFilesGetResDto> materialFiles) {
		this.materialFiles = materialFiles;
	}

}
