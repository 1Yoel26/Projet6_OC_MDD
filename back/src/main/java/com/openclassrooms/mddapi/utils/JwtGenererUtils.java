package com.openclassrooms.mddapi.utils;

import java.awt.RenderingHints.Key;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtGenererUtils {
	
	@Value("${jwt.secret}")
	private String cleSecreteJwt;
	
	@Value("${jwt.expirationMs}")
	private long tempsExpirationJwt;
	
	public String genererJwt(String email) {
		
		SecretKey cleSecreteHmac = Keys.hmacShaKeyFor(cleSecreteJwt.getBytes(StandardCharsets.UTF_8));
				
		return Jwts.builder()
				.setSubject(email)
				.setIssuedAt(new Date())
				.setExpiration(new Date( new Date().getTime() + tempsExpirationJwt))
				.signWith(cleSecreteHmac, SignatureAlgorithm.HS512)
				.compact();
		
	}

}
