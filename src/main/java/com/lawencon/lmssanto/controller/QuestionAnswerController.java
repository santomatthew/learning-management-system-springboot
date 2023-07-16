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
import com.lawencon.lmssanto.dto.questionanswer.AnswersGetReqDto;
import com.lawencon.lmssanto.dto.questionanswer.AnswersGetResDto;
import com.lawencon.lmssanto.dto.questionanswer.AnswersInsertReqDto;
import com.lawencon.lmssanto.service.StudentService;
import com.lawencon.lmssanto.service.TeacherService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("question-answers")
public class QuestionAnswerController {

	private final TeacherService teacherService;
	private final StudentService studentService;

	public QuestionAnswerController(TeacherService teacherService, StudentService studentService) {
		this.teacherService = teacherService;
		this.studentService = studentService;
	}

	@GetMapping
	public ResponseEntity<List<AnswersGetResDto>> getQuestionAnswersByTeacher(@RequestParam Long reviewId) {
		final AnswersGetReqDto data = new AnswersGetReqDto();
		data.setReviewId(reviewId);
		final List<AnswersGetResDto> response = teacherService.getQuestionAnswers(data);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertResDto> insertQuestionAnswers(@RequestBody AnswersInsertReqDto data) {
		final InsertResDto response = studentService.createAnswer(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
