package com.lawencon.lmssanto.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lmssanto.dto.InsertResDto;
import com.lawencon.lmssanto.dto.material.MaterialsGetReqDto;
import com.lawencon.lmssanto.dto.material.MaterialsGetResDto;
import com.lawencon.lmssanto.dto.material.MaterialsInsertReqDto;
import com.lawencon.lmssanto.service.StudentService;
import com.lawencon.lmssanto.service.TeacherService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("materials")
public class MaterialController {

	private TeacherService teacherService;
	private StudentService studentService;

	public MaterialController(TeacherService teacherService, StudentService studentService) {
		this.teacherService = teacherService;
		this.studentService = studentService;
	}

	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody MaterialsInsertReqDto data) {
		final InsertResDto response = teacherService.createMaterial(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<MaterialsGetResDto>> getAll(@RequestParam Long elearningId) {
		final MaterialsGetReqDto data = new MaterialsGetReqDto();
		data.setElearningId(elearningId);
		final List<MaterialsGetResDto> response = studentService.getAllMaterial(data);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
