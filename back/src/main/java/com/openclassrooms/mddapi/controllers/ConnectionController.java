package com.openclassrooms.mddapi.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.openclassrooms.mddapi.dto.UserConnectionDto;
import com.openclassrooms.mddapi.utils.JwtGenererUtils;

/**
 * Contrôleur REST chargé de la gestion de l'authentification des utilisateurs.
 * <p>
 * Ce contrôleur permet :
 * <ul>
 *   <li>L'authentification d'un utilisateur via son identifiant (username ou email) et son mot de passe</li>
 *   <li>La génération et le renvoi d'un token JWT en cas de connexion réussie</li>
 * </ul>
 * <p>
 * Il s'appuie sur Spring Security pour l'authentification
 * et sur {@link JwtGenererUtils} pour la création du token JWT.
 */

@RequestMapping("/api/connection")
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class ConnectionController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtGenererUtils jwtGenererUtils;
	
	/**
     * Authentifie un utilisateur et génère un token JWT.
     *
     * @param infoConnection données de connexion de l'utilisateur
     *                      (username ou email, mot de passe)
     * @return un token JWT si l'authentification est réussie,
     *         HTTP 400 en cas d'identifiants incorrects
     */
	@PostMapping("")
	public ResponseEntity<?> connection(@RequestBody UserConnectionDto infoConnection){
		
		String usernameOuEmail = infoConnection.getUsernameOuEmail();
		String motDePasse = infoConnection.getMotDePasse();
		
		// tentative de connection au compte si les infos sont correct:
		try {
			
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(usernameOuEmail, motDePasse) );
			
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			
			String email = userDetails.getUsername();
			
			String jwtGenerer = jwtGenererUtils.genererJwt(email);
			
			return ResponseEntity.status(200).body(Map.of("token", jwtGenerer));
			
		} 
		// en cas d'echec de connection car id ou mdp incorrect:
		catch (BadCredentialsException e) {
			
			return ResponseEntity.status(400).body(e.getMessage());
		}
		
		
	}

}
