package com.lawencon.lmssanto.dao.impl.springdatajpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.UserDao;
import com.lawencon.lmssanto.model.Profile;
import com.lawencon.lmssanto.model.Role;
import com.lawencon.lmssanto.model.User;
import com.lawencon.lmssanto.repo.UserRepo;

@Repository
@org.springframework.context.annotation.Profile("springdatajpa-query")
public class UserDaoSpringDataJPAImpl implements UserDao {

	@PersistenceContext
	private EntityManager em;
	private UserRepo userRepo;

	public UserDaoSpringDataJPAImpl(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public List<User> getAll() {
		final List<User> users = userRepo.findAll();

		return users;
	}

	@Override
	public User getByEmail(String email) {
		final User user = userRepo.getByUserEmail(email);

		return user;

	}

	@Override
	public User insert(User user) {
		userRepo.save(user);
		return user;
	}

	@Override
	public List<User> getAllByRoleCode(String roleCode) {
		final List<?> userObjs = userRepo.getByRoleCode(roleCode);

		final List<User> users = new ArrayList<>();
		if (userObjs.size() > 0) {
			for (Object userObj : userObjs) {
				final Object[] userArr = (Object[]) userObj;
				final User user = new User();
				user.setId(Long.valueOf(userArr[0].toString()));

				final Role role = new Role();
				role.setId(Long.valueOf(userArr[1].toString()));
				role.setRoleCode(userArr[2].toString());
				user.setRole(role);

				final Profile profile = new Profile();
				profile.setId(Long.valueOf(userArr[3].toString()));
				profile.setFullName(userArr[4].toString());
				user.setProfile(profile);

				users.add(user);
			}
		}

		return users;
	}

	@Override
	public User getById(Long id) {
		final User user = userRepo.getById(id);
		return user;
	}

}
