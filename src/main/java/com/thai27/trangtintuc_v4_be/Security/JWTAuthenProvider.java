package com.thai27.trangtintuc_v4_be.Security;

import com.thai27.trangtintuc_v4_be.ServiceImplement.UserDetailServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JWTAuthenProvider implements AuthenticationProvider {

	@Autowired
	UserDetailServiceImplement userDetailSrvImp;

	@Autowired
	PasswordEncoder encoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = String.valueOf(authentication.getPrincipal());
		String password = String.valueOf(authentication.getCredentials());
		UserDetails userDetail = userDetailSrvImp.loadUserByUsername(username);
		if (userDetail != null) {
			if (encoder.matches(password, userDetail.getPassword())) {
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password,
						new ArrayList<>());
				return token;
			}
		}
		throw new BadCredentialsException("Mật khẩu không chính xác");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}

}
