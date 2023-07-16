package com.lawencon.lmssanto.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lmssanto.dto.questiontype.QuestionTypeGetResDto;
import com.lawencon.lmssanto.service.TeacherService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("question-types")
public class QuestionTypeController {

	private TeacherService teacherService;

	public QuestionTypeController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	
	@GetMapping
	public ResponseEntity<List<QuestionTypeGetResDto>> getAll(){
		final List<QuestionTypeGetResDto> response = teacherService.getAllQuestionTypes();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
}
