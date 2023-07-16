package com.lawencon.lmssanto.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.RoleDao;
import com.lawencon.lmssanto.model.Role;

@Repository
@Profile("native-query")
public class RoleDaoImpl implements RoleDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Role getRole(String roleCode) {
		final String sql = "SELECT tr.id, tr.role_code, tr.ver FROM t_role tr WHERE tr.role_code = :roleCode";

		final Object roleObj = this.em.createNativeQuery(sql).setParameter("roleCode", roleCode).getSingleResult();

		final Object[] roleArr = (Object[]) roleObj;

		Role role = null;
		if (roleArr.length>0) {
			role = new Role();
			role.setId(Long.valueOf(roleArr[0].toString()));
			role.setRoleCode(roleArr[1].toString());
			role.setVer(Integer.valueOf(roleArr[2].toString()));
		}

		return role;

	}

}
