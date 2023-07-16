package com.lawencon.lmssanto.controller;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lmssanto.dto.email.LoginReqDto;
import com.lawencon.lmssanto.dto.email.LoginResDto;
import com.lawencon.lmssanto.service.JwtService;
import com.lawencon.lmssanto.service.LoginService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("login")
public class LoginController {

	private final LoginService loginService;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;

	public LoginController(LoginService loginService, AuthenticationManager authenticationManager,
			JwtService jwtService) {
		this.loginService = loginService;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}

	@PostMapping
	public ResponseEntity<?> login(@Valid @RequestBody final LoginReqDto user) throws SQLException {
		final Authentication auth = new UsernamePasswordAuthenticationToken(user.getUserEmail(),
				user.getUserPassword());

		authenticationManager.authenticate(auth);
		final LoginResDto userDb = loginService.login(user);

		final Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY, 1);

		final Map<String, Object> claims = new HashMap<>();
		claims.put("exp", cal.getTime());
		claims.put("id", userDb.getUserId());

		final LoginResDto loginRes = new LoginResDto();
		loginRes.setToken(jwtService.generateJwt(claims));
		loginRes.setFullName(userDb.getFullName());
		loginRes.setUserId(userDb.getUserId());
		loginRes.setRoleCode(userDb.getRoleCode());

		return new ResponseEntity<>(loginRes, HttpStatus.OK);
	}
}