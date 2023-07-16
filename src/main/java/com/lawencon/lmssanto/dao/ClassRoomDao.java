package com.lawencon.lmssanto.dao;

import java.util.List;

import com.lawencon.lmssanto.model.ClassRoom;

public interface ClassRoomDao {

	ClassRoom insert(ClassRoom classRoom);

	List<ClassRoom> getByTeacherId(Long teacherId);

	List<ClassRoom> getAll();

	ClassRoom getById(Long classRoomId);
	
	List<ClassRoom> getByClassRoomEnroll(Long studentId);
}
