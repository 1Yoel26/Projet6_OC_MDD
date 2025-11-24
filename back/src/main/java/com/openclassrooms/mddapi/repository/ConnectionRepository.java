package com.openclassrooms.mddapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.models.User;

@Repository
public interface ConnectionRepository extends JpaRepository<User, Long> {
	
	public User findByEmail(String email);
	
	public User findByUsername(String username);
}
