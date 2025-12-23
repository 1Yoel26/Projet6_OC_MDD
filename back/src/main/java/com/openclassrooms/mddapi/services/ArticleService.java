package com.openclassrooms.mddapi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.ArticleCreationDto;
import com.openclassrooms.mddapi.models.Abonnement;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.AbonnementRepository;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.UserRepository;

/**
 * Service chargé de la gestion des articles.
 * <p>
 * Ce service centralise toute la logique métier liée aux articles :
 * <ul>
 *   <li>Création d'un article</li>
 *   <li>Récupération des articles avec différents tris</li>
 *   <li>Récupération des articles en fonction des abonnements de l'utilisateur</li>
 *   <li>Consultation d'un article complet</li>
 * </ul>
 * <p>
 * Il interagit avec les repositories et s'appuie sur le contexte de sécurité
 * pour identifier l'utilisateur connecté.
 */
@Service
public class ArticleService {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private AbonnementRepository abonnementRepository;
	
	@Autowired
	private ThemeRepository themeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	/**
     * Crée un nouvel article à partir des informations fournies.
     * <p>
     * L'article est automatiquement associé :
     * <ul>
     *   <li>au thème sélectionné</li>
     *   <li>à l'utilisateur actuellement connecté</li>
     * </ul>
     *
     * @param infoArticle données nécessaires à la création de l'article
     * @return {@code true} si la création est réussie,
     *         {@code false} si le thème n'existe pas
     */
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
	
	
	
	 /**
     * Récupère la liste de tous les articles triés du plus récent au plus ancien.
     *
     * @return liste des articles triés par date décroissante
     */
	public List<Article> listeDesArticlesRecent() {
		
		return articleRepository.findAllByOrderByDateDesc();
		
	}
	
	
	 /**
     * Récupère la liste de tous les articles triés du plus ancien au plus récent.
     *
     * @return liste des articles triés par date croissante
     */
	public List<Article> listeDesArticlesAncien() {
			
		return articleRepository.findAllByOrderByDateAsc();
		
	}
	
	
	/**
     * Récupère la liste des articles correspondant aux thèmes
     * auxquels l'utilisateur est abonné, triés du plus récent au plus ancien.
     *
     * @return liste des articles abonnés triés par date décroissante
     */
	public List<Article> listeDesArticlesAbonneTrieDuPlusRecent(){
		
		// Récupérer l'utilisateur connecté
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String emailUserConnecte = userDetails.getUsername();
					
		User userConnecte = userRepository.findByEmail(emailUserConnecte);
		
		Long idUserConnecte = userConnecte.getId();
					
		
		// 1️ Récupérer tous les abonnements de l'utilisateur
        List<Abonnement> listeDesAbonnements = abonnementRepository.findByUserId(idUserConnecte);

        // 2️ Récupérer la liste des IDs de thèmes
        List<Long> listeDesIdsDesThemesAbonne = listeDesAbonnements.stream()
                                        .map(a -> a.getTheme().getId())
                                        .collect(Collectors.toList());

        // 3️ Récupérer tous les articles dont le thème correspond
        return articleRepository.findByThemeIdInOrderByDateAsc(listeDesIdsDesThemesAbonne);
	}
	
	
	/**
     * Récupère la liste des articles correspondant aux thèmes
     * auxquels l'utilisateur est abonné, triés du plus ancien au plus récent.
     *
     * @return liste des articles abonnés triés par date croissante
     */
	public List<Article> listeDesArticlesAbonneTrieDuMoinsRecent(){
		
		// Récupérer l'utilisateur connecté
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String emailUserConnecte = userDetails.getUsername();
					
		User userConnecte = userRepository.findByEmail(emailUserConnecte);
		
		Long idUserConnecte = userConnecte.getId();
					
		
		// 1️ Récupérer tous les abonnements de l'utilisateur
        List<Abonnement> listeDesAbonnements = abonnementRepository.findByUserId(idUserConnecte);

        // 2️ Récupérer la liste des IDs de thèmes
        List<Long> listeDesIdsDesThemesAbonne = listeDesAbonnements.stream()
                                        .map(a -> a.getTheme().getId())
                                        .collect(Collectors.toList());

        // 3️ Récupérer tous les articles dont le thème correspond
        return articleRepository.findByThemeIdInOrderByDateDesc(listeDesIdsDesThemesAbonne);
	}
	
	
	 /**
     * Récupère un article complet à partir de son identifiant.
     *
     * @param idArticle identifiant de l'article
     * @return l'article s'il existe, {@code null} sinon
     */
	public Article unArticleComplet(Long idArticle) {
		
		return articleRepository.findById(idArticle)
				.orElse(null);
		
	}

}
