package com.lawencon.lmssanto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lmssanto.dto.InsertResDto;
import com.lawencon.lmssanto.dto.user.RegisterReqDto;
import com.lawencon.lmssanto.service.LoginService;
import com.lawencon.lmssanto.service.SuperAdminService;

@RestController
@RequestMapping("register")
public class RegisterController {

	private final LoginService loginService;
	private final SuperAdminService superAdminService;

	public RegisterController(LoginService loginService, SuperAdminService superAdminService) {
		this.loginService = loginService;
		this.superAdminService = superAdminService;
	}

	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody RegisterReqDto user) {
		final InsertResDto response = loginService.register(user);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PostMapping("/teacher")
	public ResponseEntity<InsertResDto> insertTeacher(@RequestBody RegisterReqDto user) {
		final InsertResDto response = superAdminService.registerTeacher(user);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
}
