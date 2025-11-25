package com.openclassrooms.mddapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.models.Abonnement;
import com.openclassrooms.mddapi.models.AbonnementId;

@Repository
public interface AbonnementRepository extends JpaRepository<Abonnement, AbonnementId>{

	public List<Abonnement> findByUserId(Long user_id);
}
