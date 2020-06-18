package com.spring.springauthentication.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.spring.springauthentication.dto.MyUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//Basic idea for Create, Validate JWT token
@Service
public class JwtUtil {
	private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);
	public String generateToken(MyUserDetails myUserDetails) {

		Map<String, Object> claims = new HashMap<>();		
		return createToken(claims,myUserDetails.getUsername());
	}
	
	//Token expired in 10 hours
	private String createToken(Map<String, Object> claims,String subject) {		
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, "SECRET_KEY").compact();
	}
	
	private Claims extractAllClaims(String token){
		return Jwts.parser().setSigningKey("SECRET_KEY").parseClaimsJws(token).getBody();
	}
	
	private <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
		final Claims claims = extractAllClaims(token);			
		return claimsResolver.apply(claims);
		
	}

	public String extractUsername(String token) {		
		return extractClaim(token,Claims::getSubject);
	}
	
	
	private Date extractExpiration(String token){
		Date date = extractClaim(token,Claims::getExpiration);
		log.info(" extractExpiration : "+date);
		return date;
	}
	
	private boolean isTokenExpired(String token) {		
		boolean isTokenExpired = extractExpiration(token).before(new Date());
		log.info(" isTokenExpired : "+isTokenExpired);
		return isTokenExpired;
	}
	
	public boolean validateToken(String token,MyUserDetails myUserDetails) {
		final String username = extractUsername(token);
		boolean isvalidateToken = (username.equals(myUserDetails.getUsername()) && !isTokenExpired(token));
		log.info(" isvalidateToken : "+isvalidateToken);
		return isvalidateToken;		
	}
}
