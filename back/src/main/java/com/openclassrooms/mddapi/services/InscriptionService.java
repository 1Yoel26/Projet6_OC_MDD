package com.openclassrooms.mddapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.UserInscriptionDto;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.InscriptionRepository;

@Service
public class InscriptionService {
	
	@Autowired
	private InscriptionRepository inscriptionRepository;
	
	@Autowired 
	PasswordEncoder passwordEncoder;
	
	public boolean inscriptionCompte(UserInscriptionDto infoUser) {
		
		boolean emailDejaPris = inscriptionRepository.existsByEmail(infoUser.getEmail());
		
		boolean usernameDejaPris = inscriptionRepository.existsByUsername(infoUser.getUsername());
		
		// si le mail est déjà utilisé par un autre compte:
		if(emailDejaPris == true || usernameDejaPris == true) {
			return false; // ne crée pas le compte car l'email ou l'username sont indisponible
		}
		
		User userComplet = new User();
		
		userComplet.setUsername(infoUser.getUsername());
		userComplet.setEmail(infoUser.getEmail());
		userComplet.setMot_de_passe(passwordEncoder.encode(infoUser.getMotDePasse()));
		
		// si le mail est dispo, crée le compte et return true:
		inscriptionRepository.save(userComplet);
		return true;
		
	}

}
