package com.openclassrooms.mddapi.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "abonnements")
public class Abonnement {
	
	@EmbeddedId
	private AbonnementId id;
	
	@ManyToOne
	@JoinColumn(name = "id_user")
	@MapsId("id_user")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "id_theme")
	@MapsId("id_theme")
	private Theme theme;

}
