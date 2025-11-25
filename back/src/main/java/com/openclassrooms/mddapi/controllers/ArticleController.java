package com.openclassrooms.mddapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.openclassrooms.mddapi.dto.ArticleCreationDto;
import com.openclassrooms.mddapi.services.ArticleService;

@Controller
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
	

}
