package com.cedge.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	 private final String SECRET_KEY = "secret";

	    public String generateToken(String username) {
	    	System.out.println(Jwts.builder()
	                .setSubject(username)
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) 
	                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
	                .compact());
	        return Jwts.builder()
	                .setSubject(username)
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
	                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
	                .compact();
	    }

	    public String extractUsername(String token) {
	    	System.out.println(Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject());
	        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
	    }

	    public boolean isTokenValid(String token, String username) {
	    	System.out.println(extractUsername(token).equals(username) && !isTokenExpired(token));
	        return extractUsername(token).equals(username) && !isTokenExpired(token);
	    }

	    private boolean isTokenExpired(String token) {
	    	System.out.println(Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getExpiration().before(new Date()));
	        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getExpiration().before(new Date());
	    }

}
