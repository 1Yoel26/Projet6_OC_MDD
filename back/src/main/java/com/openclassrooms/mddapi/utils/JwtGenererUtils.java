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

/**
 * Utilitaire pour générer des JSON Web Tokens (JWT).
 * 
 * Cette classe permet de créer des JWT afin d'authentifier et de vérifier
 * l'utilisateur pour chaque requête HTTP vers l'API backend.
 * 
 * Le JWT contient :
 * <ul>
 *   <li>Le sujet : l'email de l'utilisateur</li>
 *   <li>La date de création</li>
 *   <li>La date d'expiration</li>
 * </ul>
 * 
 * La clé secrète utilisée pour signer le token est stockée dans
 * <code>application.properties</code> et sert à garantir que le token
 * n'a pas été falsifié.
 */
@Service
public class JwtGenererUtils {
	
	@Value("${jwt.secret}")
	private String cleSecreteJwt;
	
	@Value("${jwt.expirationMs}")
	private long tempsExpirationJwt;
	
	/**
     * Génère un JWT pour un utilisateur donné.
     * 
     * @param email l'email de l'utilisateur pour lequel générer le JWT
     * @return le JWT généré sous forme de chaîne
     */
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
