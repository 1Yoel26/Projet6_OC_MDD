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

/**
 * Contrôleur REST chargé de la gestion de l'inscription des utilisateurs.
 * <p>
 * Ce contrôleur permet :
 * <ul>
 *   <li>La création d'un nouveau compte utilisateur</li>
 *   <li>La validation des données d'inscription</li>
 * </ul>
 * <p>
 * Toute la logique métier liée à l'inscription est déléguée
 * au {@link InscriptionService}.
 */
@RestController
@RequestMapping("api/inscription")
@CrossOrigin(origins = "*", maxAge = 3600)
public class InscriptionController {
	
	private final InscriptionService inscriptionService;
	
	public InscriptionController(InscriptionService inscriptionService) {
		this.inscriptionService = inscriptionService;
	}
	
	/**
     * Crée un nouveau compte utilisateur.
     *
     * @param infoUser données nécessaires à l'inscription de l'utilisateur
     * @return HTTP 200 si la création du compte est réussie,
     *         HTTP 400 si les données sont invalides ou le compte existe déjà
     */
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
