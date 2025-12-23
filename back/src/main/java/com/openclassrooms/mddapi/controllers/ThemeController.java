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

/**
 * Contrôleur REST chargé de la gestion des thèmes et des abonnements.
 * <p>
 * Ce contrôleur permet :
 * <ul>
 *   <li>La récupération de la liste des thèmes disponibles</li>
 *   <li>La récupération des thèmes auxquels l'utilisateur est abonné</li>
 *   <li>L'abonnement et le désabonnement à un thème</li>
 * </ul>
 * <p>
 * Toute la logique métier liée aux thèmes et aux abonnements
 * est déléguée au {@link ThemeService}.
 */
@RestController
@RequestMapping("/api/theme")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ThemeController {
	
	@Autowired
	private ThemeService themeService;
	
	/**
     * Récupère la liste de tous les thèmes disponibles.
     *
     * @return liste des thèmes
     */
	@GetMapping("/lesThemes")
	public List<Theme> listeDesThemes(){
		
		return themeService.listeDesThemes();
		
	}
	
	 /**
     * Récupère la liste des identifiants des thèmes
     * auxquels l'utilisateur est abonné.
     *
     * @return liste des identifiants de thèmes abonnés
     */
	@GetMapping("/lesIdThemesAbonne")
	public List<Long> listeDesIdDeThemeAbonne(){
		
		return themeService.listeDesIdThemesAbonne();
		
	}
	
	 /**
     * Récupère la liste des abonnements de l'utilisateur aux thèmes.
     *
     * @return liste des abonnements aux thèmes
     */
	@GetMapping("/lesThemesAbonne")
	public List<Abonnement> listeDesThemeAbonne(){
		
		return themeService.listeDesThemesAbonne();
		
	}
	
	/**
     * Abonne l'utilisateur à un thème donné.
     *
     * @param idTheme identifiant du thème auquel s'abonner
     * @return HTTP 200 si l'abonnement est réussi,
     *         HTTP 400 en cas d'échec
     */
	@PostMapping("/abonnementAunTheme/{idTheme}")
	public ResponseEntity<?> abonnementAUnTheme(@PathVariable Long idTheme){
		
		boolean succesAbonnementAUnTheme = themeService.abonnerAUnTheme(idTheme);
		
		if(succesAbonnementAUnTheme) {
			return ResponseEntity.status(200).build();
		}
		
		return ResponseEntity.status(400).build();
	}
	
	
	/**
     * Désabonne l'utilisateur d'un thème donné.
     *
     * @param idTheme identifiant du thème dont se désabonner
     * @return HTTP 200 si le désabonnement est réussi,
     *         HTTP 400 en cas d'échec
     */
	@DeleteMapping("/desabonnementAunTheme/{idTheme}")
	public ResponseEntity<?> desabonnementAUnTheme(@PathVariable Long idTheme){
		
		boolean succesDesabonnementAUnTheme = themeService.desabonnerAUnTheme(idTheme);
		
		if(succesDesabonnementAUnTheme) {
			return ResponseEntity.status(200).build();
		}
		
		return ResponseEntity.status(400).build();
	}

}
