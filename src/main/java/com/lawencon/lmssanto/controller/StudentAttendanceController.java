package com.lawencon.lmssanto.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lmssanto.dto.InsertResDto;
import com.lawencon.lmssanto.dto.UpdateResDto;
import com.lawencon.lmssanto.dto.studentattendance.StudentAttInsertReqDto;
import com.lawencon.lmssanto.dto.studentattendance.StudentAttUpdateReqDto;
import com.lawencon.lmssanto.dto.studentattendance.StudentAttsGetResDto;
import com.lawencon.lmssanto.service.StudentService;
import com.lawencon.lmssanto.service.TeacherService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("student-attendances")
public class StudentAttendanceController {

	private TeacherService teacherService;
	private StudentService studentService;

	public StudentAttendanceController(TeacherService teacherService, StudentService studentService) {
		this.teacherService = teacherService;
		this.studentService = studentService;
	}

	@GetMapping
	public ResponseEntity<List<StudentAttsGetResDto>> getAll(@RequestParam Long elearningId) {
		final List<StudentAttsGetResDto> response = teacherService.getStudentAttendance(elearningId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PatchMapping()
	public ResponseEntity<UpdateResDto> update(@RequestBody StudentAttUpdateReqDto data) {
		final UpdateResDto response = teacherService.updateStudentAttendance(data);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody StudentAttInsertReqDto data) {
		final InsertResDto response = studentService.createStudentAttendance(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
