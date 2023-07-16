package com.lawencon.lmssanto.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lmssanto.dto.question.QuestionsGetReqDto;
import com.lawencon.lmssanto.dto.question.QuestionsGetResDto;
import com.lawencon.lmssanto.service.StudentService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("questions")
public class QuestionController {

	private final StudentService studentService;

	public QuestionController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping
	public ResponseEntity<List<QuestionsGetResDto>> getByTaskId(@RequestParam Long taskId) {
		final QuestionsGetReqDto data = new QuestionsGetReqDto();
		data.setTaskId(taskId);
		final List<QuestionsGetResDto> response = studentService.getQuestions(data);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
