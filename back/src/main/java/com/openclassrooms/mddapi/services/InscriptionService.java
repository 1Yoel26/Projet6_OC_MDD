package com.openclassrooms.mddapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.UserInscriptionDto;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.InscriptionRepository;

/**
 * Service gérant l'inscription des utilisateurs.
 * 
 * Ce service permet :
 * <ul>
 *   <li>Vérifier la disponibilité d'un email et d'un username</li>
 *   <li>Encoder le mot de passe de l'utilisateur</li>
 *   <li>Créer un nouvel utilisateur dans la base de données</li>
 * </ul>
 */
@Service
public class InscriptionService {
	
	@Autowired
	private InscriptionRepository inscriptionRepository;
	
	@Autowired 
	PasswordEncoder passwordEncoder;
	
	/**
     * Crée un compte utilisateur à partir des informations fournies.
     * 
     * Cette méthode vérifie d'abord si l'email ou le username est déjà utilisé.
     * Si l'un ou l'autre est indisponible, la création échoue.
     * Sinon, elle encode le mot de passe et sauvegarde le nouvel utilisateur.
     *
     * @param infoUser objet {@link UserInscriptionDto} contenant username, email et mot de passe
     * @return true si le compte a été créé avec succès, false si l'email ou le username est déjà pris
     */
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
