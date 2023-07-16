package com.lawencon.lmssanto.dao;

import java.util.List;

import com.lawencon.lmssanto.model.Elearning;

public interface ElearningDao {

	Elearning insert(Elearning elearning);

	List<Elearning> getByClassRoom(Long classRoomId);
	
	Elearning getById(Long id);
}
