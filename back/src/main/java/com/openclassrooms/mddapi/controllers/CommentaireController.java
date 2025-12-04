package com.openclassrooms.mddapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.CommentaireCreationDto;
import com.openclassrooms.mddapi.models.Commentaire;
import com.openclassrooms.mddapi.services.CommentaireService;

@RequestMapping("/api/commentaire")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CommentaireController {
	
	@Autowired
	private CommentaireService commentaireService;
	
	@PostMapping("/creationCommentaire")
	public ResponseEntity<?> creationCommentaire(@RequestBody CommentaireCreationDto infoCommentaireDto){
		
		boolean succesCreationCommentaire = commentaireService.creationCommentaire(infoCommentaireDto);
		
		if(succesCreationCommentaire == false) {
			return ResponseEntity.status(400).build();
		}
		
		return ResponseEntity.status(200).build();
		
	}
	
	@GetMapping("/listeDesCommentaires/{idArticle}")
	public ResponseEntity<?> listeDesCommentaires(@PathVariable Long idArticle){
		
		List<Commentaire> listeDesCommentaires = commentaireService.listeDesCommentairesSurCetArticle(idArticle);
		
		if(listeDesCommentaires != null) {
			return ResponseEntity.ok(listeDesCommentaires);
		}
		
		return ResponseEntity.status(400).build();
	}
	

}
