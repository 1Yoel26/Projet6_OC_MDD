package com.openclassrooms.mddapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.ArticleCreationDto;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.UserRepository;

@Service
public class ArticleService {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private ThemeRepository themeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public boolean creation(ArticleCreationDto infoArticle) {
		
		Article articleACreer = new Article();
		
		boolean themeExist = themeRepository.existsById(infoArticle.getId_theme());
		
		if(themeExist == true) {
			
			Theme themeACreer = themeRepository.findById(infoArticle.getId_theme())
					.orElseThrow(() -> new RuntimeException("Theme introuvable"));
			
			// Récupérer l'utilisateur connecté
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String emailUserConnecte = userDetails.getUsername();
			
			User userConnecte = userRepository.findByEmail(emailUserConnecte);
			
			articleACreer.setTheme(themeACreer);
			
			articleACreer.setUser(userConnecte);
			
			articleACreer.setTitre(infoArticle.getTitre());
			
			articleACreer.setContenu(infoArticle.getContenu());
			
			articleRepository.save(articleACreer);
			
			return true;
			
		}else {
			return false;
		}
		
	}

}
