package com.openclassrooms.mddapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.models.Abonnement;
import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.services.ThemeService;

@RestController
@RequestMapping("/api/theme")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ThemeController {
	
	@Autowired
	private ThemeService themeService;
	
	@GetMapping("/lesThemes")
	public List<Theme> listeDesThemes(){
		
		return themeService.listeDesThemes();
		
	}
	
	@GetMapping("/lesIdThemesAbonne")
	public List<Long> listeDesIdDeThemeAbonne(){
		
		return themeService.listeDesIdThemesAbonne();
		
	}
	
	@GetMapping("/lesThemesAbonne")
	public List<Abonnement> listeDesThemeAbonne(){
		
		return themeService.listeDesThemesAbonne();
		
	}
	
	
	@PostMapping("/abonnementAunTheme/{idTheme}")
	public ResponseEntity<?> abonnementAUnTheme(@PathVariable Long idTheme){
		
		boolean succesAbonnementAUnTheme = themeService.abonnerAUnTheme(idTheme);
		
		if(succesAbonnementAUnTheme) {
			return ResponseEntity.status(200).build();
		}
		
		return ResponseEntity.status(400).build();
	}
	
	
	@DeleteMapping("/desabonnementAunTheme/{idTheme}")
	public ResponseEntity<?> desabonnementAUnTheme(@PathVariable Long idTheme){
		
		boolean succesDesabonnementAUnTheme = themeService.desabonnerAUnTheme(idTheme);
		
		if(succesDesabonnementAUnTheme) {
			return ResponseEntity.status(200).build();
		}
		
		return ResponseEntity.status(400).build();
	}

}
