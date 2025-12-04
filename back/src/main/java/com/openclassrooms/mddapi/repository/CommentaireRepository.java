package com.openclassrooms.mddapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.models.Commentaire;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Long>{
	
	// retourne tous les commentaires sur l'article selectionné:
	public List<Commentaire> findByArticleId(Long idArticle);
	
}
