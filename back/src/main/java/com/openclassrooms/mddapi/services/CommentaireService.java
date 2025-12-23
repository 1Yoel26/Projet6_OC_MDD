package com.openclassrooms.mddapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.CommentaireCreationDto;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.Commentaire;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.CommentaireRepository;
import com.openclassrooms.mddapi.repository.UserRepository;

/**
 * Service chargé de la gestion des commentaires.
 * <p>
 * Ce service contient la logique métier liée aux commentaires :
 * <ul>
 *   <li>Création d'un commentaire sur un article</li>
 *   <li>Récupération des commentaires associés à un article</li>
 * </ul>
 * <p>
 * Il s'appuie sur le contexte de sécurité pour identifier l'utilisateur connecté
 * et sur les repositories pour l'accès aux données.
 */
@Service
public class CommentaireService {
	
	@Autowired
	private CommentaireRepository commentaireRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	/**
     * Crée un nouveau commentaire sur un article.
     * <p>
     * Le commentaire est automatiquement associé :
     * <ul>
     *   <li>à l'utilisateur actuellement connecté</li>
     *   <li>à l'article concerné</li>
     * </ul>
     *
     * @param infoCommentaire données nécessaires à la création du commentaire
     * @return {@code true} si la création est réussie,
     *         {@code false} si l'article n'existe pas ou en cas d'échec
     */
	public boolean creationCommentaire(CommentaireCreationDto infoCommentaire) {
		
		Commentaire unCommentaire = new Commentaire();
		
		User userConnecte = new User();
		
		Article unArticle = new Article();
		
		// Récupérer l'utilisateur connecté
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String emailUserConnecte = userDetails.getUsername();
				
		userConnecte = userRepository.findByEmail(emailUserConnecte);
		
		// récuperation de l'article sur lequel est posté ce commentaire:
		unArticle = articleRepository.findById(infoCommentaire.getIdArticle()).orElse(null);
		
		// si l'id de l'article n'existe pas (peu probable, mais au cas où):
		if(unArticle == null) {
			return false;
		}
		
		unCommentaire.setUser(userConnecte);
		unCommentaire.setArticle(unArticle);
		unCommentaire.setContenu(infoCommentaire.getContenu());
		
		Commentaire commentaireBienEnregistre = commentaireRepository.save(unCommentaire);
		
		// si le commentaire à bien été enregistré:
		if(commentaireBienEnregistre.getId() != null) {
			return true;
		}
		
		// sinon si ça à échoué:
		return false;
		
	}
	   
	
	 /**
     * Récupère la liste des commentaires associés à un article.
     *
     * @param idArticle identifiant de l'article
     * @return liste des commentaires liés à l'article
     */
	public List<Commentaire> listeDesCommentairesSurCetArticle(Long idArticle){
		
		List<Commentaire> listeDesCommentaires = commentaireRepository.findByArticleId(idArticle);
		
		return listeDesCommentaires;
	}

}
