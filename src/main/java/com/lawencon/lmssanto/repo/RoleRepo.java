package com.lawencon.lmssanto.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long>{

	@Query(value = "SELECT tr.id, tr.role_code FROM t_role tr WHERE tr.role_code = :roleCode",nativeQuery = true)
	Object getByRoleCode(String  roleCode);
	
}
