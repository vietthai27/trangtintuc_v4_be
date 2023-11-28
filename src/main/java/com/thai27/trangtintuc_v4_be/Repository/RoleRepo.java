package com.thai27.trangtintuc_v4_be.Repository;

import com.thai27.trangtintuc_v4_be.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

	List<Role> findByRolename(String userRole);

	@Query(value = "SELECT  r.role_name\n" +
			"\tFROM role r, trangtintuc_user u, trangtintuc_user_roles ur\n" +
			"where \n" +
			"u.id = ur.trang_tin_tuc_users_id and\n" +
			"r.id = ur.roles_id and\n" +
			"u.username = :username",nativeQuery = true)
	List<String> findRoleByUsername(@Param("username") String username);

	@Query(value = "INSERT INTO trangtintuc_user_roles(\n" +
			"\ttrang_tin_tuc_users_id, roles_id)\n" +
			"\tVALUES (:userId, 2)",nativeQuery = true)
	@Modifying
	@Transactional
	void setModerRole (@Param("userId") Long userId);
}
