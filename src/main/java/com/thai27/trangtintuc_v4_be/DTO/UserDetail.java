package com.thai27.trangtintuc_v4_be.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.thai27.trangtintuc_v4_be.Entity.TrangTinTucUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class UserDetail implements UserDetails {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	@JsonIgnore
	private String password;


	private Collection<? extends GrantedAuthority> authorities;

	public UserDetail(Long id, String username, String password, String email,
                      Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserDetail build(TrangTinTucUser user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRolename())).collect(Collectors.toList());
		return new UserDetail(user.getId(),user.getUsername(),user.getPassword(),user.getEmail(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
