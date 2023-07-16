package com.lawencon.lmssanto.service;

import java.util.List;

import com.lawencon.lmssanto.dto.InsertResDto;
import com.lawencon.lmssanto.dto.classroom.ClassRoomInsertReqDto;
import com.lawencon.lmssanto.dto.user.RegisterReqDto;
import com.lawencon.lmssanto.dto.user.TeachersResDto;
import com.lawencon.lmssanto.model.Role;

public interface SuperAdminService {

	InsertResDto registerTeacher(RegisterReqDto registerReqDto);

	Role getRole(String roleCode);

	InsertResDto createClassRoomAndAssignTeacher(ClassRoomInsertReqDto classRoomInsertReqDto);

	List<TeachersResDto> getAllTeacher(String roleCode);
}
