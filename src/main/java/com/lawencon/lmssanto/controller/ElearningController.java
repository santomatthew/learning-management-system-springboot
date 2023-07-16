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
import com.lawencon.lmssanto.dto.elearning.ElearningInsertReqDto;
import com.lawencon.lmssanto.dto.elearning.ElearningsGetResDto;
import com.lawencon.lmssanto.service.StudentService;
import com.lawencon.lmssanto.service.TeacherService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("elearnings")
public class ElearningController {

	private TeacherService teacherService;
	private StudentService studentService;

	public ElearningController(TeacherService teacherService, StudentService studentService) {
		this.teacherService = teacherService;
		this.studentService = studentService;
	}

	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody ElearningInsertReqDto data) {
		final InsertResDto response = teacherService.createElearning(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<ElearningsGetResDto>> getByClassRoomId(@RequestParam Long classRoomId) {
		final List<ElearningsGetResDto> response = teacherService.getElearningsByClassRoom(classRoomId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/show")
	public ResponseEntity<List<ElearningsGetResDto>> getByEnrolledClassRoom(@RequestParam Long classRoomId) {
		final List<ElearningsGetResDto> response = studentService.getElearningsByClassRoom(classRoomId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
