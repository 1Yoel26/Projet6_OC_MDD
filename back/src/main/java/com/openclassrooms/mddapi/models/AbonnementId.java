package com.openclassrooms.mddapi.models;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class AbonnementId implements Serializable {
	
	private Long id_user;
	private Long id_theme;

}
