package com.lawencon.lmssanto.dao.impl.hql;

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
@org.springframework.context.annotation.Profile("hql-query")
public class UserDaoHQLImpl implements UserDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<User> getAll()  {
		final String sql = "SELECT u FROM User u";
		final List<User> users = this.em.createQuery(sql, User.class).getResultList();

		return users;
	}

	@Override
	public User getByEmail(String email) {
		final String sql = "SELECT tu.id, tu.role.id, tu.role.roleCode, tu.profile.id, tu.profile.fullName, "
				+ "tu.userPassword FROM User tu " + " WHERE tu.userEmail = :email ";

		final Object userObj = this.em.createQuery(sql).setParameter("email", email).getSingleResult();

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
	public User insert(User user)  {
		em.persist(user);
		return user;
	}

	@Override
	public List<User> getAllByRoleCode(String roleCode)  {
		final String sql = "SELECT tu.id, tu.role.id, tu.role.roleCode, tu.profile.id, tu.profile.fullName "
				+ " FROM User tu " + " WHERE tu.role.roleCode = :roleCode";

		final List<?> userObjs = this.em.createQuery(sql).setParameter("roleCode", roleCode).getResultList();

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
	public User getById(Long id)  {
		final User user = this.em.find(User.class, id);
		return user;
	}

}
