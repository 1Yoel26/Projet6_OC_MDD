package com.openclassrooms.mddapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.UserInfoDto;
import com.openclassrooms.mddapi.dto.UserInscriptionDto;
import com.openclassrooms.mddapi.services.UserService;

/**
 * Contrôleur REST chargé de la gestion des informations utilisateur.
 * <p>
 * Ce contrôleur permet :
 * <ul>
 *   <li>La récupération des informations de l'utilisateur connecté</li>
 *   <li>La modification du profil de l'utilisateur connecté</li>
 * </ul>
 * <p>
 * Toute la logique métier liée à l'utilisateur
 * est déléguée au {@link UserService}.
 */
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
	
	@Autowired
	private UserService userService;
	
	 /**
     * Récupère les informations de l'utilisateur actuellement connecté.
     *
     * @return les informations de l'utilisateur connecté
     */
	@GetMapping("/infoUserConnecte")
	public UserInfoDto infoUserConnecte() {
		
		return userService.infoDuUser();
		
	}
	
	/**
     * Modifie les informations du profil de l'utilisateur connecté.
     * <p>
     * Cette opération permet de mettre à jour l'email,
     * le nom d'utilisateur et le mot de passe.
     *
     * @param infoModifProfil nouvelles informations du profil utilisateur
     * @return HTTP 200 si la modification est réussie,
     *         HTTP 400 si l'email est déjà utilisé par un autre utilisateur
     */
	@PutMapping("/modificationInfoUserConnecte")
	public ResponseEntity<?> modificationProfil(@RequestBody UserInscriptionDto infoModifProfil){
		
		boolean emailValideModificationProfil = userService.modificationDuProfil(infoModifProfil);
		
		// si l'email n'est pas validé car il est déjà pris par un autre utilisateur :
		if(!emailValideModificationProfil) {
			return ResponseEntity.status(400).build();
		}
		
		// sinon si l'email est dispo:
		return ResponseEntity.status(200).build();
	}

}
