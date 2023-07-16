package com.lawencon.lmssanto.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.lmssanto.constant.UserRole;
import com.lawencon.lmssanto.dao.ClassRoomDao;
import com.lawencon.lmssanto.dao.FileDao;
import com.lawencon.lmssanto.dao.ProfileDao;
import com.lawencon.lmssanto.dao.RoleDao;
import com.lawencon.lmssanto.dao.UserDao;
import com.lawencon.lmssanto.dto.InsertResDto;
import com.lawencon.lmssanto.dto.classroom.ClassRoomInsertReqDto;
import com.lawencon.lmssanto.dto.user.RegisterReqDto;
import com.lawencon.lmssanto.dto.user.TeachersResDto;
import com.lawencon.lmssanto.model.ClassRoom;
import com.lawencon.lmssanto.model.File;
import com.lawencon.lmssanto.model.Profile;
import com.lawencon.lmssanto.model.Role;
import com.lawencon.lmssanto.model.User;
import com.lawencon.lmssanto.service.PrincipalService;
import com.lawencon.lmssanto.service.SendMailService;
import com.lawencon.lmssanto.service.SuperAdminService;
import com.lawencon.lmssanto.util.GeneratorUtil;

@Service
public class SuperAdminServiceImpl implements SuperAdminService {

	private final UserDao userDao;
	private final ProfileDao profileDao;
	private final FileDao fileDao;
	private final RoleDao roleDao;
	private final ClassRoomDao classRoomDao;
	private final PrincipalService principalService;
	private final SendMailService sendMailService;
	private final PasswordEncoder passwordEncoder;

	@PersistenceContext
	private EntityManager em;

	public SuperAdminServiceImpl(UserDao userDao, ProfileDao profileDao, FileDao fileDao, RoleDao roleDao,
			ClassRoomDao classRoomDao, PrincipalService principalService, SendMailService sendMailService,
			PasswordEncoder passwordEncoder) {
		this.userDao = userDao;
		this.profileDao = profileDao;
		this.fileDao = fileDao;
		this.roleDao = roleDao;
		this.classRoomDao = classRoomDao;
		this.principalService = principalService;
		this.sendMailService = sendMailService;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional
	@Override
	public InsertResDto registerTeacher(RegisterReqDto registerReqDto) {
		final InsertResDto insertResDto = new InsertResDto();

		final Long person = principalService.getPrincipal();
		final User admin = userDao.getById(person);

		// Insert File
		File newFile = new File();
		newFile.setFileName(registerReqDto.getPhotoName());
		newFile.setExt(registerReqDto.getExt());
		newFile.setCreatedBy(admin.getId());
		newFile = fileDao.insert(newFile);

		// Insert Profile
		Profile profile = new Profile();
		profile.setPhoto(newFile);
		profile.setFullName(registerReqDto.getFullName());
		profile.setAddress(registerReqDto.getAddress());
		profile.setPhoneNo(registerReqDto.getPhone());
		profile.setCreatedBy(admin.getId());
		profile = profileDao.insert(profile);

		User teacher = new User();
		teacher.setUserEmail(registerReqDto.getUserEmail());
		
		final String generatePassword = GeneratorUtil.generateCode();

		final String bodyEmail = "Halo " + registerReqDto.getFullName()
				+ ", Akun kamu berhasil dibuat dengan password : " + generatePassword;

		sendMailService.sendEmail(registerReqDto.getUserEmail(), "Account Registration", bodyEmail);
		final String encodedPassword = passwordEncoder.encode(generatePassword);

		teacher.setUserPassword(encodedPassword);
		teacher.setProfile(profile);
		final Role role = roleDao.getRole(UserRole.TEACHER.roleCode);
		teacher.setRole(role);
		teacher.setCreatedBy(admin.getId());

		// Insert User
		teacher = userDao.insert(teacher);

		insertResDto.setId(teacher.getId());
		insertResDto.setMessage("Berhasil mendaftarkan teacher");

		return insertResDto;
	}

	@Override
	public Role getRole(String roleCode) {
		final Role role = roleDao.getRole(roleCode);
		return role;
	}

	@Transactional
	@Override
	public InsertResDto createClassRoomAndAssignTeacher(ClassRoomInsertReqDto classRoomInsertReqDto) {
		final InsertResDto insertRes = new InsertResDto();
		final Long person = principalService.getPrincipal();
		final User admin = userDao.getById(person);
		// Insert classroom photo
		File photo = new File();
		photo.setFileName(classRoomInsertReqDto.getPhoto());
		photo.setExt(classRoomInsertReqDto.getExt());
		photo = fileDao.insert(photo);

		final User teacher = userDao.getById(classRoomInsertReqDto.getTeacherId());
		// Insert ClassRoom
		ClassRoom newClassRoom = new ClassRoom();
		newClassRoom.setClassroomName(classRoomInsertReqDto.getClassroomName());
		newClassRoom.setClassroomCode(classRoomInsertReqDto.getClassroomCode());
		newClassRoom.setPhoto(photo);
		newClassRoom.setTeacher(teacher);
		newClassRoom.setCreatedBy(admin.getId());

		newClassRoom = classRoomDao.insert(newClassRoom);

		insertRes.setId(newClassRoom.getId());
		insertRes.setMessage("Berhasil membuat kelas dan assign teacher " + teacher.getProfile().getFullName());
		return insertRes;
	}

	@Override
	public List<TeachersResDto> getAllTeacher(String roleCode) {
		final List<User> allTeachers = userDao.getAllByRoleCode(roleCode);
		final List<TeachersResDto> teachers = new ArrayList<>();

		for (int i = 0; i < allTeachers.size(); i++) {
			final TeachersResDto teacher = new TeachersResDto();
			teacher.setId(allTeachers.get(i).getId());
			teacher.setTeacherName(allTeachers.get(i).getProfile().getFullName());
			teachers.add(teacher);
		}

		return teachers;
	}

}
