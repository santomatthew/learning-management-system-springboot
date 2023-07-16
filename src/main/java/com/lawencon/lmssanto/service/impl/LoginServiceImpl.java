package com.lawencon.lmssanto.service.impl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.lmssanto.constant.UserRole;
import com.lawencon.lmssanto.dao.FileDao;
import com.lawencon.lmssanto.dao.ProfileDao;
import com.lawencon.lmssanto.dao.RoleDao;
import com.lawencon.lmssanto.dao.UserDao;
import com.lawencon.lmssanto.dto.InsertResDto;
import com.lawencon.lmssanto.dto.email.LoginReqDto;
import com.lawencon.lmssanto.dto.email.LoginResDto;
import com.lawencon.lmssanto.dto.user.RegisterReqDto;
import com.lawencon.lmssanto.model.File;
import com.lawencon.lmssanto.model.Profile;
import com.lawencon.lmssanto.model.Role;
import com.lawencon.lmssanto.model.User;
import com.lawencon.lmssanto.service.LoginService;
import com.lawencon.lmssanto.service.PrincipalService;
import com.lawencon.lmssanto.service.SendMailService;
import com.lawencon.lmssanto.util.GeneratorUtil;

@Service
public class LoginServiceImpl implements LoginService {

	private final UserDao userDao;
	private final ProfileDao profileDao;
	private final FileDao fileDao;
	private final RoleDao roleDao;
	private final SendMailService sendMailService;
	private final PasswordEncoder passwordEncoder;

	@PersistenceContext
	private EntityManager em;

	public LoginServiceImpl(UserDao userDao, ProfileDao profileDao, FileDao fileDao, RoleDao roleDao,
			PrincipalService principalService, SendMailService sendMailService, PasswordEncoder passwordEncoder) {
		this.userDao = userDao;
		this.profileDao = profileDao;
		this.fileDao = fileDao;
		this.roleDao = roleDao;
		this.sendMailService = sendMailService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public LoginResDto login(LoginReqDto loginData) {
		final User user = userDao.getByEmail(loginData.getUserEmail());
		final LoginResDto userDto = new LoginResDto();
		userDto.setUserId(user.getId());
		userDto.setFullName(user.getProfile().getFullName());
		userDto.setRoleCode(user.getRole().getRoleCode());
		return userDto;

	}

	@Transactional
	@Override
	public InsertResDto register(RegisterReqDto user) {
		final InsertResDto insertRes = new InsertResDto();

		// Insert File
		File newFile = new File();
		newFile.setFileName(user.getFullName());
		newFile.setExt(user.getExt());
		newFile.setCreatedBy(0l);

		newFile = fileDao.insert(newFile);

		// Insert Profile
		Profile profile = new Profile();
		profile.setPhoto(newFile);
		profile.setFullName(user.getFullName());
		profile.setAddress(user.getAddress());
		profile.setPhoneNo(user.getPhone());
		profile.setCreatedBy(0l);
		profile = profileDao.insert(profile);

		final Role role = roleDao.getRole(UserRole.STUDENT.roleCode);
		// Insert User
		User newUser = new User();
		newUser.setUserEmail(user.getUserEmail());
		newUser.setRole(role);
		newUser.setProfile(profile);
		final String generatePassword = GeneratorUtil.generateCode();

		final String bodyEmail = "Halo " + user.getFullName() + ", Akun kamu berhasil dibuat dengan password : "
				+ generatePassword;

		sendMailService.sendEmail(user.getUserEmail(), "Account Registration", bodyEmail);
		final String encodedPassword = passwordEncoder.encode(generatePassword);

		newUser.setUserPassword(encodedPassword);
		newUser.setCreatedBy(0l);

		newUser = userDao.insert(newUser);
		// Update User
		final User userDb = userDao.getById(newUser.getId());
		userDb.setCreatedBy(newUser.getId());
		userDb.setUpdatedBy(newUser.getId());

		final File fileDb = fileDao.getById(newFile.getId());
		fileDb.setCreatedBy(newUser.getId());
		fileDb.setUpdatedBy(newUser.getId());

		final Profile profileDb = profileDao.getById(profile.getId());
		profileDb.setCreatedBy(newUser.getId());
		profileDb.setUpdatedBy(newUser.getId());

		insertRes.setId(newUser.getId());
		insertRes.setMessage("Berhasil register akun");

		return insertRes;
	}

	@Override
	public Role getRole(String roleCode) {
		final Role role = roleDao.getRole(roleCode);
		return role;
	}

	@Override
	public User insertFileAndProfileAndUpdateUser(User user) {
		User updatedUser = null;

		try {
			this.em.getTransaction().begin();

			this.em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
		}

		return updatedUser;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User user = userDao.getByEmail(username);

		if (user != null) {
			return new org.springframework.security.core.userdetails.User(username, user.getUserPassword(),
					new ArrayList<>());
		}

		throw new UsernameNotFoundException("Email / Password salah");
	}

}
