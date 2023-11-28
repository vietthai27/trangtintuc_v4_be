package com.thai27.trangtintuc_v4_be.ServiceImplement;

import com.thai27.trangtintuc_v4_be.DTO.UserDetail;
import com.thai27.trangtintuc_v4_be.Entity.TrangTinTucUser;
import com.thai27.trangtintuc_v4_be.Repository.TrangTinTucUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailServiceImplement implements UserDetailsService{

	@Autowired
	TrangTinTucUserRepo trangTinTucUserRepo;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TrangTinTucUser userInfo = trangTinTucUserRepo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Tài khoản " + username + " không tồn tại trong hệ thống"));
		return UserDetail.build(userInfo);
	}

}
