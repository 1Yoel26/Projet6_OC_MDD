package com.openclassrooms.mddapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		
		return themeService.listeDesIdThemes();
		
	}

}
