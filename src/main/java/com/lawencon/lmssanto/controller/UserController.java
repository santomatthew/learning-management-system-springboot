package com.lawencon.lmssanto.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lmssanto.dto.user.TeachersResDto;
import com.lawencon.lmssanto.service.SuperAdminService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("users")
public class UserController {

	private SuperAdminService superAdminService;

	public UserController(SuperAdminService superAdminService) {
		this.superAdminService = superAdminService;

	}
	
	@GetMapping
	public ResponseEntity<List<TeachersResDto>> getTeachers(@RequestParam String roleCode){
		final List<TeachersResDto> response = superAdminService.getAllTeacher(roleCode);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
