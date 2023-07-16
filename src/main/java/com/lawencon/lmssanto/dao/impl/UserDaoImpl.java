package com.lawencon.lmssanto.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.UserDao;
import com.lawencon.lmssanto.model.Profile;
import com.lawencon.lmssanto.model.Role;
import com.lawencon.lmssanto.model.User;

@Repository
@org.springframework.context.annotation.Profile("native-query")
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<User> getAll() {
		final String sql = "SELECT * FROM t_user";
		final List<User> users = this.em.createNativeQuery(sql, User.class).getResultList();

		return users;
	}

	@Override
	public User getByEmail(String email) {
		final String sql = "SELECT tu.id, tu.role_id, tr.role_code, tu.profile_id, tp.full_name, tu.user_password FROM t_user tu "
				+ " INNER JOIN t_role tr ON tr.id = tu.role_id " + " INNER JOIN t_profile tp ON tp.id = tu.profile_id "
				+ " WHERE tu.user_email = :email ";

		final Object userObj = this.em.createNativeQuery(sql).setParameter("email", email).getSingleResult();

		final Object[] userArr = (Object[]) userObj;

		User user = null;

		if (userArr.length > 0) {
			user = new User();
			user.setId(Long.valueOf(userArr[0].toString()));

			final Role role = new Role();
			role.setId(Long.valueOf(userArr[1].toString()));
			role.setRoleCode(userArr[2].toString());
			user.setRole(role);

			final Profile profile = new Profile();
			profile.setId(Long.valueOf(userArr[3].toString()));
			profile.setFullName(userArr[4].toString());
			user.setProfile(profile);
			user.setUserPassword(userArr[5].toString());
		}

		return user;

	}

	@Override
	public User insert(User user) {
		em.persist(user);
		return user;
	}

	@Override
	public List<User> getAllByRoleCode(String roleCode) {
		final String sql = "SELECT tu.id, tu.role_id, tr.role_code, tu.profile_id, tp.full_name ,tu.ver FROM t_user tu "
				+ " INNER JOIN t_role tr ON tr.id = tu.role_id " + " INNER JOIN t_profile tp ON tp.id = tu.profile_id "
				+ " WHERE tr.role_code = :roleCode";

		final List<?> userObjs = this.em.createNativeQuery(sql).setParameter("roleCode", roleCode).getResultList();

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
				user.setVer(Integer.valueOf(userArr[5].toString()));

				users.add(user);
			}
		}

		return users;
	}

	@Override
	public User getById(Long id) {
		final User user = this.em.find(User.class, id);
		return user;
	}

}
