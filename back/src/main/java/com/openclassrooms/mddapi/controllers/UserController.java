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

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/infoUserConnecte")
	public UserInfoDto infoUserConnecte() {
		
		return userService.infoDuUser();
		
	}
	
	
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
