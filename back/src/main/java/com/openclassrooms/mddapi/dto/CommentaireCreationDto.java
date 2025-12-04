package com.openclassrooms.mddapi.dto;

import lombok.Data;

@Data
public class CommentaireCreationDto {
	
	private Long idArticle;
	private String contenu;

}
