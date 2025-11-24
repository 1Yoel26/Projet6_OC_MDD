package com.openclassrooms.mddapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.repository.ThemeRepository;

@Service
public class ThemeService {
	
	@Autowired
	private ThemeRepository themeRepository;
	
	public List<Theme> listeDesIdThemes(){
		
		return themeRepository.findAll();
		
	}

}
