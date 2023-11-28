package com.thai27.trangtintuc_v4_be.Security;

import com.thai27.trangtintuc_v4_be.ServiceImplement.UserDetailServiceImplement;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class JWTTokenFilter extends OncePerRequestFilter {

	@Autowired
	JWTUltil jwt;

	@Autowired
	UserDetailServiceImplement userSrv;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorHeader = request.getHeader("Authorization");
		if (authorHeader == null || authorHeader.isEmpty() || !authorHeader.startsWith("Bearer")) {
			filterChain.doFilter(request, response);
			return;
		}
		String token = authorHeader.split(" ")[1].trim();
		if (!jwt.validate(token)) {
			filterChain.doFilter(request, response);
			return;
		}
		String username = jwt.getUsername(token);
		UserDetails userInfo = userSrv.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null,
				userInfo.getAuthorities());
		authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authToken);
		filterChain.doFilter(request, response);
	}


}
