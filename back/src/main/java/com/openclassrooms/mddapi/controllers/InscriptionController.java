package com.openclassrooms.mddapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.UserInscriptionDto;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.services.InscriptionService;

@RestController
@RequestMapping("api/inscription")
@CrossOrigin(origins = "*", maxAge = 3600)
public class InscriptionController {
	
	private final InscriptionService inscriptionService;
	
	public InscriptionController(InscriptionService inscriptionService) {
		this.inscriptionService = inscriptionService;
	}
	
	
	@PostMapping("")
	public ResponseEntity<Void> inscription(@RequestBody UserInscriptionDto infoUser){
		
		boolean creationDeCompteReussi = inscriptionService.inscriptionCompte(infoUser);
		
		// si la creation de compte à réussi:
		if(creationDeCompteReussi == true) {
			return ResponseEntity.ok().build();
		}
		else if(creationDeCompteReussi == false) {
			return ResponseEntity.status(400).build();
		}
		
		return ResponseEntity.status(500).build();
		
	}

}
