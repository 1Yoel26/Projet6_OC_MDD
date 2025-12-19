package com.openclassrooms.mddapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.UserInfoDto;
import com.openclassrooms.mddapi.dto.UserInscriptionDto;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.utils.JwtGenererUtils;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtGenererUtils jwtGenererUtils;
	
	public UserInfoDto infoDuUser() {
		
		User userConnecte;
		
		// récupération de l'user connecté pour pouvoir récupérer la liste de ses id de themes auquelles il est abonné:
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
				
		userConnecte = userRepository.findByEmail(userDetails.getUsername());
				
		UserInfoDto userInfoDto = new UserInfoDto();
		
		userInfoDto.setUsername(userConnecte.getUsername());
		userInfoDto.setEmail(userConnecte.getEmail());
		
		return userInfoDto;
	}
	
	public boolean modificationDuProfil(UserInscriptionDto infoUser) {
		
		
		boolean emailValide = true;
		boolean usernameValide = true;
		
		User userConnecte;
		
		// récupération de l'user connecté pour pouvoir récupérer la liste de ses id de themes auquelles il est abonné:
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
				
		userConnecte = userRepository.findByEmail(userDetails.getUsername());
		
		String emailAncien = userConnecte.getEmail();
		String emailNouveau = infoUser.getEmail();
		

		String usernameAncien = userConnecte.getUsername();
		String usernameNouveau = infoUser.getUsername();
		
		// si le nouveau mail est different de l'ancien:
		if(!emailNouveau.equals(emailAncien)) {
			User userAvecNouveauEmail = userRepository.findByEmail(emailNouveau);
			
			
			
			// Et si le nouveau mail est deja pris, renvois false sans rien modifié:
			if(userAvecNouveauEmail != null) {
				emailValide = false;
				
				// return immediat pour ne pas lancer la modif du profil en bdd dans ce cas.
				return emailValide;
			}
			
		}
		
		
		// si le nouveau Username est different de l'ancien:
		if(!usernameNouveau.equals(usernameAncien)) {
			User userAvecNouveauUsername = userRepository.findByUsername(usernameNouveau);
					
			
					
			// Et si le nouveau username est deja pris, renvois false sans rien modifié:
			if(userAvecNouveauUsername != null) {
				usernameValide = false;
						
				// return immediat pour ne pas lancer la modif du profil en bdd dans ce cas.
				return usernameValide;
			}
					
		}
		
		// si le nouveau email est validé, faire la modification du profil:
		
		userConnecte.setUsername(infoUser.getUsername());
		
		userConnecte.setEmail(infoUser.getEmail());
		
		// si un nouveau mot de passe à été définis, encodage puis modif du mot de passe aussi:
		if(infoUser.getMotDePasse() != null && !infoUser.getMotDePasse().trim().isEmpty()) {
			
			userConnecte.setMot_de_passe(passwordEncoder.encode(infoUser.getMotDePasse()));
		}
		
		
		// Modification du profil en Bdd :
		userRepository.save(userConnecte);
		
		return emailValide;
	}

}
