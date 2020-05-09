package com.apple.spring.config.security.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtils {
	final String SECRET_KEY="secret";
	public String generateToken(UserDetails userDetails) {
		Map<String,Object> claims=new HashMap<String,Object>();
		return createToken(claims,userDetails.getUsername());
	}

	public String createToken(Map<String, Object> claims, String username) {
		// TODO Auto-generated method stub
		return Jwts.builder().setClaims(claims).setSubject(username).
				setIssuedAt(new Date(System.currentTimeMillis())).
				setExpiration(new Date(System.currentTimeMillis()*1000*60*60*10)).
				signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
	public boolean validateToken(String token,UserDetails userDetails) {
		String username=extractUsername(token);
		return username.equals(userDetails.getUsername())&& !isTokenExpired(token);
	}
	public String extractUsername(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token, Claims::getSubject);
	}

	public boolean isTokenExpired(String token) {
			return extractExpiration(token).before(new Date());
	}
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	public <T> T extractClaim(String token,Function<Claims,T> claimsResolver) {
		final Claims claim=extractAllClaims(token);
		return claimsResolver.apply(claim);
	}
	public Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
}
