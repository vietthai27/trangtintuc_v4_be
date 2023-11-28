package com.thai27.trangtintuc_v4_be.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;



@Service
public class JWTUltil {
	private static final int expireInMs = 6000 * 1000;

	private final static String key = "superwierdandlongkeyohmygodwhythishastobesolong";

	public String generate(String username) {
		return Jwts.builder().setSubject(username).setIssuer("thai27").setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expireInMs))
				.signWith(getSigningKey()).compact();
	}



	 private Key getSigningKey() {
		  byte[] keyBytes = Decoders.BASE64.decode(key);
		  return Keys.hmacShaKeyFor(keyBytes);
		}

	public boolean validate(String token) {
		if (getUsername(token) != null && isExpired(token)) {
			return true;
		}
		return false;
	}

	public String getUsername(String token) {
		Claims claims = getClaims(token);
		return claims.getSubject();
	}

	public boolean isExpired(String token) {
		Claims claims = getClaims(token);
		return claims.getExpiration().after(new Date(System.currentTimeMillis()));
	}

	private Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
	}

}
