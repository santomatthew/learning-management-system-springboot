package com.lawencon.lmssanto.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.lawencon.lmssanto.dto.InsertResDto;
import com.lawencon.lmssanto.dto.email.LoginReqDto;
import com.lawencon.lmssanto.dto.email.LoginResDto;
import com.lawencon.lmssanto.dto.user.RegisterReqDto;
import com.lawencon.lmssanto.model.Role;
import com.lawencon.lmssanto.model.User;

public interface LoginService extends UserDetailsService {

	LoginResDto login(LoginReqDto loginData);

	InsertResDto register(RegisterReqDto user);

	Role getRole(String roleCode);

	User insertFileAndProfileAndUpdateUser(User user);

}
