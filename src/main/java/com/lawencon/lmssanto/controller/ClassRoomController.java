package com.lawencon.lmssanto.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lmssanto.dto.InsertResDto;
import com.lawencon.lmssanto.dto.classroom.AllClassRoomGetResDto;
import com.lawencon.lmssanto.dto.classroom.ClassRoomGetResDto;
import com.lawencon.lmssanto.dto.classroom.ClassRoomInsertReqDto;
import com.lawencon.lmssanto.service.StudentService;
import com.lawencon.lmssanto.service.SuperAdminService;
import com.lawencon.lmssanto.service.TeacherService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("classrooms")
public class ClassRoomController {

	private SuperAdminService superAdminService;
	private TeacherService teacherService;
	private StudentService studentService;

	public ClassRoomController(SuperAdminService superAdminService, TeacherService teacherService,
			StudentService studentService) {
		this.superAdminService = superAdminService;
		this.teacherService = teacherService;
		this.studentService = studentService;
	}

	@PostMapping
	public ResponseEntity<InsertResDto> assignTeacher(@RequestBody ClassRoomInsertReqDto data) {
		final InsertResDto response = superAdminService.createClassRoomAndAssignTeacher(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<ClassRoomGetResDto>> getClassRooms(){
		final List<ClassRoomGetResDto> response = teacherService.getClassRoom();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/show")
	public ResponseEntity<List<AllClassRoomGetResDto>> getClassRoomByStudent(){
		final List<AllClassRoomGetResDto> response = studentService.getClassRooms();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
