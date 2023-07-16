package com.lawencon.lmssanto.dto.email;

import javax.validation.constraints.NotBlank;

public class LoginReqDto {

	@NotBlank(message="Email tidak boleh kosong")
	private String userEmail;
	
	@NotBlank(message="Password tidak boleh kosong")
	private String userPassword;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

}
