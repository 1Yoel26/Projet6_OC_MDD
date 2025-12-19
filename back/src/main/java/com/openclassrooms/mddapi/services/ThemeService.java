package com.openclassrooms.mddapi.services;

import java.security.Security;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.models.Abonnement;
import com.openclassrooms.mddapi.models.AbonnementId;
import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.AbonnementRepository;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.UserRepository;

@Service
public class ThemeService {
	
	@Autowired
	private ThemeRepository themeRepository;
	
	@Autowired
	private AbonnementRepository abonnementRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public List<Theme> listeDesThemes(){
		
		return themeRepository.findAll();
		
	}
	
	public List<Long> listeDesIdThemesAbonne(){
		
		User userConnecte = new User();
		
		// récupération de l'user connecté pour pouvoir récupérer la liste de ses id de themes auquelles il est abonné:
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		userConnecte = userRepository.findByEmail(userDetails.getUsername());
		
		// recuperation de la liste de ses abonnements:
		List<Abonnement> listeDesAbonnements = abonnementRepository.findByUserId(userConnecte.getId());
	
		// recuperation de la liste de ces ids de themes abonné:
		List<Long> listeDesIdDeThemeAbonne = listeDesAbonnements.stream()
				.map(elt -> elt.getTheme().getId())
				.collect(Collectors.toList());
		
		return listeDesIdDeThemeAbonne;
	}
	
	
	
	public List<Abonnement> listeDesThemesAbonne(){
		
		User userConnecte = new User();
		
		// récupération de l'user connecté pour pouvoir récupérer la liste de ses id de themes auquelles il est abonné:
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		userConnecte = userRepository.findByEmail(userDetails.getUsername());
		
		// recuperation de la liste de ses abonnements:
		List<Abonnement> listeDesAbonnements = abonnementRepository.findByUserId(userConnecte.getId());
	
		
		
		return listeDesAbonnements;
	}
	
	
	public boolean abonnerAUnTheme(Long idTheme) {
		
		User userConnecte = new User();
		
		// récupération de l'user connecté pour pouvoir récupérer la liste de ses id de themes auquelles il est abonné:
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		userConnecte = userRepository.findByEmail(userDetails.getUsername());
		
		// recuperation de la liste de ses abonnements:
		List<Abonnement> listeDesAbonnements = abonnementRepository.findByUserId(userConnecte.getId());
	
		// recuperation de la liste de ces ids de themes abonné:
		List<Long> listeDesIdDeThemeAbonne = listeDesAbonnements.stream()
				.map(elt -> elt.getTheme().getId())
				.collect(Collectors.toList());
		
		// si l'utilisateur est déja abonné a cet article (peu probable 
		if(listeDesIdDeThemeAbonne.contains(idTheme)) {
			return false;
		}
		
		Abonnement unAbonnement = new Abonnement();
		
		
		Theme unTheme = new Theme();
		
		unTheme = themeRepository.findById(idTheme)
				.orElse(null);
		
		AbonnementId id = new AbonnementId();
	    id.setId_user(userConnecte.getId());
	    id.setId_theme(unTheme.getId());
	    
	    unAbonnement.setId(id);
		
		unAbonnement.setUser(userConnecte);
		
		unAbonnement.setTheme(unTheme);
		
		
		abonnementRepository.save(unAbonnement);
		
		return true;
		
	}
	
	
	public boolean desabonnerAUnTheme(Long idTheme) {
		
		User userConnecte = new User();
		
		// récupération de l'user connecté pour pouvoir récupérer la liste de ses id de themes auquelles il est abonné:
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		userConnecte = userRepository.findByEmail(userDetails.getUsername());
		
		// recuperation de la liste de ses abonnements:
		List<Abonnement> listeDesAbonnements = abonnementRepository.findByUserId(userConnecte.getId());
	
		// recuperation de la liste de ces ids de themes abonné:
		List<Long> listeDesIdDeThemeAbonne = listeDesAbonnements.stream()
				.map(elt -> elt.getTheme().getId())
				.collect(Collectors.toList());
		
		// si l'utilisateur n'est pas abonné a cet article (peu probable mais au cas ou):
		if(!listeDesIdDeThemeAbonne.contains(idTheme)) {
			return false;
		}
		
		AbonnementId unAbonnementId = new AbonnementId();
		
		unAbonnementId.setId_user(userConnecte.getId());
		unAbonnementId.setId_theme(idTheme);
		
		abonnementRepository.deleteById(unAbonnementId);
		
		return true;
		
	}
	

}
