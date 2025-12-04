package com.openclassrooms.mddapi.utils;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtValiderUtil {
	
	@Value("${jwt.secret}")
	private String cleSecreteJwt;
	
	@Value("${jwt.expirationMs}")
	private long tempsExpirationJwt;
	
	
	public String validationJwt(String jwt) {
		
		SecretKey cleSecreteHmac = Keys.hmacShaKeyFor(cleSecreteJwt.getBytes(StandardCharsets.UTF_8));
		
		// tentative de validation du jwt :
		try {
			
			return Jwts.parserBuilder()
			.setSigningKey(cleSecreteHmac)
			.build()
			.parseClaimsJws(jwt)
			.getBody()
			.getSubject();
			
			
		} 
		
		// en cas de token non validé, indiquer l'erreur du jwt:
		catch (ExpiredJwtException e) {
			
            System.out.println("JWT expiré");
            
        } catch (JwtException e) {
        	
            // Signature invalide, token corrompu, non lisible, etc.
            System.out.println("JWT invalide : " + e.getMessage());
            
        } catch (Exception e) {
        	
            // Toutes les autres erreurs
            System.out.println("Erreur inattendue : " + e.getMessage());
        }
		
		return null;
	}
	
	
	

}
