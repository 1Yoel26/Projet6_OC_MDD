package com.openclassrooms.mddapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.ConnectionRepository;

@Service
public class ConnectionService implements UserDetailsService {
	
	@Autowired
	private ConnectionRepository connectionRepository;

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
