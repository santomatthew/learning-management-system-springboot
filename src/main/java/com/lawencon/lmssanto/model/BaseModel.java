package com.lawencon.lmssanto.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

@MappedSuperclass
public class BaseModel {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(name ="created_by", nullable = false)
	private Long createdBy;
	
	@Column(name ="created_at", nullable = false)
	private LocalDateTime createdAt;
	
	@Column(name ="updated_by")
	private Long updatedBy;
	
	@Column(name ="updated_at")
	private LocalDateTime updatedAt;
	
	@Column(name ="is_active", nullable = false)
	private Boolean isActive;
	
	@Version
	private Integer ver;

	@PrePersist
	private void prePersist() {
		this.createdAt = LocalDateTime.now();
		this.isActive = true;
	}
	
	@PreUpdate
	private void preUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
	
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getVer() {
		return ver;
	}

	public void setVer(Integer ver) {
		this.ver = ver;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
