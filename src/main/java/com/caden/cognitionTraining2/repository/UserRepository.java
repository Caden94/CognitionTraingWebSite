package com.caden.cognitionTraining2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caden.cognitionTraining2.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}
