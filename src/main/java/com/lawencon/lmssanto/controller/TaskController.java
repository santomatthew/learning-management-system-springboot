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
import com.lawencon.lmssanto.dto.task.TaskInsertReqDto;
import com.lawencon.lmssanto.dto.task.TasksGetReqDto;
import com.lawencon.lmssanto.dto.task.TasksGetResDto;
import com.lawencon.lmssanto.service.StudentService;
import com.lawencon.lmssanto.service.TeacherService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("tasks")
public class TaskController {

	private TeacherService teacherService;
	private StudentService studentService;

	public TaskController(TeacherService teacherService, StudentService studentService) {
		this.teacherService = teacherService;
		this.studentService = studentService;
	}

	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody TaskInsertReqDto data) {
		final InsertResDto response = teacherService.createTask(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<TasksGetResDto>> getAll(@RequestParam Long elearningId) {
		final TasksGetReqDto data = new TasksGetReqDto();
		data.setElearningId(elearningId);
		final List<TasksGetResDto> response = teacherService.getTask(data);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/show")
	public ResponseEntity<List<TasksGetResDto>> getTasksForStudent(@RequestParam Long elearningId) {
		final TasksGetReqDto data = new TasksGetReqDto();
		data.setElearningId(elearningId);
		final List<TasksGetResDto> response = studentService.getTask(data);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
