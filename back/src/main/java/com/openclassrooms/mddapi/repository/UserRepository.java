package com.openclassrooms.mddapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.models.User;

@Service
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);
}
