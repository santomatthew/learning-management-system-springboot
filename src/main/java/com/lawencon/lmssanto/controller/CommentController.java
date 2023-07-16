package com.lawencon.lmssanto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lmssanto.dto.InsertResDto;
import com.lawencon.lmssanto.dto.comment.CommentInsertReqDto;
import com.lawencon.lmssanto.service.StudentService;
import com.lawencon.lmssanto.service.TeacherService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("comments")
public class CommentController {

	private TeacherService teacherService;
	private StudentService studentService;

	public CommentController(TeacherService teacherService, StudentService studentService) {
		this.teacherService = teacherService;
		this.studentService = studentService;
	}

	
	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody CommentInsertReqDto data) {
		final InsertResDto response = teacherService.createComment(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/create")
	public ResponseEntity<InsertResDto> insertAsStudent(@RequestBody CommentInsertReqDto data) {
		final InsertResDto response = studentService.createComment(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
