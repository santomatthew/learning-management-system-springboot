package com.lawencon.lmssanto.dao;

import java.util.List;

import com.lawencon.lmssanto.model.User;

public interface UserDao {

	List<User> getAll();

	User getByEmail(String email);

	User insert(User user);

	List<User> getAllByRoleCode(String roleCode) ;

	User getById(Long id);

}
