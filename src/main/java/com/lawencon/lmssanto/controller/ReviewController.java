package com.lawencon.lmssanto.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lmssanto.dto.UpdateResDto;
import com.lawencon.lmssanto.dto.review.ReviewUpdateReqDto;
import com.lawencon.lmssanto.dto.review.ReviewsGetReqDto;
import com.lawencon.lmssanto.dto.review.ReviewsGetResDto;
import com.lawencon.lmssanto.service.TeacherService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("reviews")
public class ReviewController {

	
	private TeacherService teacherService;

	public ReviewController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	
	@GetMapping
	public ResponseEntity<List<ReviewsGetResDto>> getAll(@RequestParam Long taskId){
		final ReviewsGetReqDto data = new ReviewsGetReqDto();
		data.setTaskId(taskId);
		final List<ReviewsGetResDto> response = teacherService.getReview(data);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PatchMapping
	public ResponseEntity<UpdateResDto> update(@RequestBody ReviewUpdateReqDto data){
		final UpdateResDto response = teacherService.updateReview(data);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
