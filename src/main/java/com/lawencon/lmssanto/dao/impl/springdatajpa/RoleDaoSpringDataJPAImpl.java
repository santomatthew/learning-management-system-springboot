package com.lawencon.lmssanto.dao.impl.springdatajpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.dao.RoleDao;
import com.lawencon.lmssanto.model.Role;
import com.lawencon.lmssanto.repo.RoleRepo;

@Repository
@Profile("springdatajpa-query")
public class RoleDaoSpringDataJPAImpl implements RoleDao {

	@PersistenceContext
	private EntityManager em;
	private RoleRepo roleRepo;

	public RoleDaoSpringDataJPAImpl(RoleRepo roleRepo) {
		this.roleRepo = roleRepo;

	}

	@Override
	public Role getRole(String roleCode)  {

		final Object roleObj =roleRepo.getByRoleCode(roleCode);

		final Object[] roleArr = (Object[]) roleObj;

		Role role = null;
		if (roleObj != null) {
			role = new Role();
			role.setId(Long.valueOf(roleArr[0].toString()));
			role.setRoleCode(roleArr[1].toString());
		}

		return role;

	}

}
