package com.lawencon.lmssanto.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.lmssanto.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

	User getByUserEmail(String email);

	@Query(value="SELECT tu.id, tu.role_id, tr.role_code, tu.profile_id, tp.full_name " + " FROM t_user tu "
				+ " INNER JOIN t_role tr ON tr.id = tu.role_id " + " INNER JOIN t_profile tp ON tp.id = tu.profile_id "
				+ " WHERE tr.role_code = :roleCode",nativeQuery = true)
	List<Object> getByRoleCode(String roleCode);
	
	User getById(Long id);

}
