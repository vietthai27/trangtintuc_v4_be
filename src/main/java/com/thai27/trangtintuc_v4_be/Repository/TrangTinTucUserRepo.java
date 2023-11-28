package com.thai27.trangtintuc_v4_be.Repository;

import com.thai27.trangtintuc_v4_be.Entity.TrangTinTucUser;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrangTinTucUserRepo extends JpaRepository<TrangTinTucUser, Long>{

	Optional<TrangTinTucUser> findByUsername(String username);

	@Query(value = "delete from trangtintuc_user_roles where trang_tin_tuc_users_id = :userId",nativeQuery = true)
	@Transactional
	@Modifying
	void deleteUserRole(@Param("userId")Long userId);

	@Query(value = "delete from trangtintuc_user_roles where trang_tin_tuc_users_id = :userId and roles_id = 2",nativeQuery = true)
	@Transactional
	@Modifying
	void deleteUserRoleModer(@Param("userId")Long userId);

	@Query(value = "delete from trangtintuc_user where id = :userId", nativeQuery = true)
	@Transactional
	@Modifying
	void deleteUser(@Param("userId")Long userId);

	Page<TrangTinTucUser> findAllByUsernameLikeIgnoreCase(String username, PageRequest pageRequest);
	
}
