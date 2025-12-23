package com.openclassrooms.mddapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.ConnectionRepository;

/**
 * Service chargé de la gestion de la connexion des utilisateurs.
 * <p>
 * Ce service implémente {@link UserDetailsService} pour Spring Security,
 * permettant l'authentification via email ou username.
 * Il fournit les informations nécessaires à Spring Security pour
 * valider les identifiants et les rôles des utilisateurs.
 */
@Service
public class ConnectionService implements UserDetailsService {
	
	@Autowired
	private ConnectionRepository connectionRepository;

	
	/**
     * Charge un utilisateur (pour pouvoir vérifier ensuite dans le ConnectionController si l'identifiant et le mot de passe sont corrects) à partir de son email ou de son username.
     * <p>
     * La méthode effectue les étapes suivantes :
     * <ul>
     *   <li>Recherche l'utilisateur par email</li>
     *   <li>Si non trouvé, recherche l'utilisateur par username</li>
     *   <li>Si toujours non trouvé, lance une exception {@link UsernameNotFoundException}</li>
     *   <li>Si trouvé, retourne un {@link UserDetails} contenant l'email, le mot de passe et les rôles</li>
     * </ul>
     *
     * @param username email ou username de l'utilisateur
     * @return {@link UserDetails} utilisé par Spring Security pour l'authentification
     * @throws UsernameNotFoundException si l'utilisateur n'existe pas
     */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// tentative de récuperation de l'user par son email : (car connection possible par email ou username) :
		User userTrouve = connectionRepository.findByEmail(username);
		
		// si aucun user est trouvé par son email, on tente par le username : 
		if(userTrouve == null) {
			
			userTrouve = connectionRepository.findByUsername(username);
			
			// si l'username et l'email de cet user sont introuvable en Bdd, retourne Erreur : 
			if(userTrouve == null) {
				throw new UsernameNotFoundException("Cette utilisateur n'existe pas :" + username);
			}
			
		}
		
		
		
		// Si l'user à bien été trouvé par son email ou son username, retourne ses infos pour spring secu :
		
		UserDetails userDetails = org.springframework.security.core.userdetails.User
				.withUsername(userTrouve.getEmail())
				.password(userTrouve.getMot_de_passe())
				.authorities("USER")
				.build();
		
		return userDetails;
	}
	

}
