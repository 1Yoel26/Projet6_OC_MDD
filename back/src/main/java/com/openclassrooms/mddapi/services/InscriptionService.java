package com.openclassrooms.mddapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.UserInscriptionDto;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.InscriptionRepository;

@Service
public class InscriptionService {
	
	@Autowired
	private InscriptionRepository inscriptionRepository;
	
	
	
	public boolean inscriptionCompte(UserInscriptionDto infoUser) {
		
		boolean emailDejaPris = inscriptionRepository.existsByEmail(infoUser.getEmail());
		
		// si le mail est déjà utilisé par un autre compte:
		if(emailDejaPris == true) {
			return false; // ne crée pas le compte car email indisponible
		}
		
		User userAEnregistre = new User();
		
		userAEnregistre.setUsername(infoUser.getUsername());
		userAEnregistre.setEmail(infoUser.getEmail());
		userAEnregistre.setMot_de_passe(infoUser.getMotDePasse());
		
		// si le mail est dispo, crée le compte et return true:
		inscriptionRepository.save(userAEnregistre);
		return true;
		
	}

}
