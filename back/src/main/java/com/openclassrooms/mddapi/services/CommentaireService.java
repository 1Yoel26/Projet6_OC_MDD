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

@Service
public class CommentaireService {
	
	@Autowired
	private CommentaireRepository commentaireRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
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
	   
	
	public List<Commentaire> listeDesCommentairesSurCetArticle(Long idArticle){
		
		List<Commentaire> listeDesCommentaires = commentaireRepository.findByArticleId(idArticle);
		
		return listeDesCommentaires;
	}

}
