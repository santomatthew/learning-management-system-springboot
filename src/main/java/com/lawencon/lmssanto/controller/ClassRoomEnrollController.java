package com.lawencon.lmssanto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lmssanto.dto.InsertResDto;
import com.lawencon.lmssanto.dto.classroomenroll.ClassRoomEnrollInsertReqDto;
import com.lawencon.lmssanto.service.StudentService;
import com.lawencon.lmssanto.service.TeacherService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("classroom-enrolls")
public class ClassRoomEnrollController {

	private StudentService studentService;

	public ClassRoomEnrollController(StudentService studentService) {
		this.studentService = studentService;
	}

	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody ClassRoomEnrollInsertReqDto data) {
		final InsertResDto response = studentService.createClassRoomEnroll(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
