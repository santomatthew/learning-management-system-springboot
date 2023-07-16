package com.lawencon.lmssanto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lmssanto.dto.forum.ForumGetReqDto;
import com.lawencon.lmssanto.dto.forum.ForumGetResDto;
import com.lawencon.lmssanto.service.StudentService;
import com.lawencon.lmssanto.service.TeacherService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("forums")
public class ForumController {

	private TeacherService teacherService;
	private StudentService studentService;

	public ForumController(TeacherService teacherService, StudentService studentService) {
		this.teacherService = teacherService;
		this.studentService = studentService;
	}
	
	@GetMapping
	public ResponseEntity<ForumGetResDto> get(@RequestParam Long elearningId){
		final ForumGetReqDto data = new ForumGetReqDto();
		data.setElearningId(elearningId);
		final ForumGetResDto response = teacherService.getForumByElearning(data);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/show")
	public ResponseEntity<ForumGetResDto> getForumAsStudent(@RequestParam Long elearningId){
		final ForumGetReqDto data = new ForumGetReqDto();
		data.setElearningId(elearningId);
		final ForumGetResDto response = studentService.getForumByElearning(data);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
