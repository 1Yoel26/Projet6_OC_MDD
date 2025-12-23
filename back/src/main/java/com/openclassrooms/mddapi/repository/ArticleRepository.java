package com.openclassrooms.mddapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.mddapi.models.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>{
	
	// trier par plus recent pour tous les articles
	public List<Article> findAllByOrderByDateDesc();
		
	// trier par moins recent pour tous les articles
	public List<Article> findAllByOrderByDateAsc();
	
	// trier par plus recent pour les articles abonnés
	public List<Article> findByThemeIdInOrderByDateAsc(List<Long> listeDesIdsDesThemes);
	
	// trier par moins recent pour les articles abonnés
	public List<Article> findByThemeIdInOrderByDateDesc(List<Long> listeDesIdsDesThemes);


}
