package com.lawencon.lmssanto.dao;

import java.util.List;

import com.lawencon.lmssanto.model.ClassRoomEnroll;

public interface ClassRoomEnrollDao {

	List<ClassRoomEnroll> getAllByStudentId(ClassRoomEnroll classRoomEnroll);

	ClassRoomEnroll insert(ClassRoomEnroll classRoomEnroll);
}
