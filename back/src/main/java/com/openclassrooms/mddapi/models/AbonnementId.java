package com.openclassrooms.mddapi.models;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class AbonnementId implements Serializable {
	
	private Long id_user;
	private Long id_theme;

}
