package com.openclassrooms.mddapi.dto;

import lombok.Data;

@Data
public class ArticleCreationDto {
	
	private Long id_theme;
	private String titre;
	private String contenu;

}
