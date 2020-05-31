package com.donte.profiles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.donte.profiles.model.UserApp;

@Repository							
public interface UserRepository extends JpaRepository<UserApp, Long>{
	UserApp findByLogin(String login);
}
