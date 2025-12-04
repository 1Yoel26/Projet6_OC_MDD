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

@RestController
@RequestMapping("/api/article")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@PostMapping("/creationArticle")
	public ResponseEntity<?> creationArticle(@RequestBody ArticleCreationDto infoArticle){
		
		boolean creationReussi = articleService.creation(infoArticle);
		
		if(creationReussi) {
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.status(400).build();
		
	}
	
	// par defaut trié par récent pour la date:
	@GetMapping("/listeDesArticles")
	public List<Article> listeDesArticles() {
		
		return articleService.listeDesArticlesRecent();
		
	}
	
	@GetMapping("/listeDesArticlesAncien")
	public List<Article> listeDesArticlesAncien() {
		
		return articleService.listeDesArticlesAncien();
		
	}
	
	@GetMapping("/listeDesArticlesAbonneRecent")
	public List<Article> listeDesArticlesAbonneRecent() {
			
		return articleService.listeDesArticlesAbonneTrieDuPlusRecent();
			
	}
	
	@GetMapping("/listeDesArticlesAbonneAncien")
	public List<Article> listeDesArticlesAbonnesAnciens() {
		
		return articleService.listeDesArticlesAbonneTrieDuMoinsRecent();
		
	}
	
	
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
