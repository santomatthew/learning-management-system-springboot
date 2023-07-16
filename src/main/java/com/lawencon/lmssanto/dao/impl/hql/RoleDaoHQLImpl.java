package com.lawencon.lmssanto.dao.impl.hql;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.RoleDao;
import com.lawencon.lmssanto.model.Role;

@Repository
@Profile("hql-query")
public class RoleDaoHQLImpl implements RoleDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Role getRole(String roleCode) {
		final String sql = "SELECT tr FROM Role tr WHERE tr.roleCode = :roleCode";

		final Role role = this.em.createQuery(sql, Role.class).setParameter("roleCode", roleCode).getSingleResult();

		return role;

	}

}
