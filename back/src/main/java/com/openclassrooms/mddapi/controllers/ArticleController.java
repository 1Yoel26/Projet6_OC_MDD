package com.openclassrooms.mddapi.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.ArticleCreationDto;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.services.ArticleService;

/**
 * Contrôleur REST chargé de la gestion des articles.
 *
 * <p>Ce contrôleur permet :</p>
 * <ul>
 *   <li>La création d'un article</li>
 *   <li>La récupération des listes d'articles avec différents tris</li>
 *   <li>La récupération d'un article complet via son identifiant</li>
 * </ul>
 *
 * <p>Il délègue toute la logique métier au {@link ArticleService}.</p>
 */

@RestController
@RequestMapping("/api/article")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	/**
     * Crée un nouvel article.
     *
     * @param infoArticle données nécessaires à la création de l'article
     * @return HTTP 200 si la création est réussie, HTTP 400 sinon
     */
	@PostMapping("/creationArticle")
	public ResponseEntity<?> creationArticle(@RequestBody ArticleCreationDto infoArticle){
		
		boolean creationReussi = articleService.creation(infoArticle);
		
		if(creationReussi) {
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.status(400).build();
		
	}
	
	/**
     * Récupère la liste de tous les articles triés du plus récent au plus ancien.
     *
     * @return liste des articles triés par date décroissante
     */
	// par defaut trié par récent pour la date:
	@GetMapping("/listeDesArticles")
	public List<Article> listeDesArticles() {
		
		return articleService.listeDesArticlesRecent();
		
	}
	

    /**
     * Récupère la liste de tous les articles triés du plus ancien au plus récent.
     *
     * @return liste des articles triés par date croissante
     */
	@GetMapping("/listeDesArticlesAncien")
	public List<Article> listeDesArticlesAncien() {
		
		return articleService.listeDesArticlesAncien();
		
	}
	
	 /**
     * Récupère la liste des articles auxquels l'utilisateur est abonné,
     * triés du plus récent au plus ancien.
     *
     * @return liste des articles abonnés triés par date décroissante
     */
	@GetMapping("/listeDesArticlesAbonneRecent")
	public List<Article> listeDesArticlesAbonneRecent() {
			
		return articleService.listeDesArticlesAbonneTrieDuPlusRecent();
			
	}
	
	/**
     * Récupère la liste des articles auxquels l'utilisateur est abonné,
     * triés du plus ancien au plus récent.
     *
     * @return liste des articles abonnés triés par date croissante
     */
	@GetMapping("/listeDesArticlesAbonneAncien")
	public List<Article> listeDesArticlesAbonnesAnciens() {
		
		return articleService.listeDesArticlesAbonneTrieDuMoinsRecent();
		
	}
	
	/**
     * Récupère un article complet à partir de son identifiant.
     *
     * @param idArticle identifiant de l'article
     * @return l'article si trouvé, HTTP 400 sinon
     */
	// par defaut trié par récent pour la date:
	@GetMapping("/{idArticle}")
	public ResponseEntity<?> unArticleComplet(@PathVariable Long idArticle) {
			
		Article unArticle =  articleService.unArticleComplet(idArticle);
		
		if(unArticle == null) {
			
			return ResponseEntity.status(400).body(unArticle);
			
		}
		
		return ResponseEntity.status(200).body(unArticle);
			
	}
	

}
