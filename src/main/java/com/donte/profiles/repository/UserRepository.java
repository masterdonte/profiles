package com.donte.profiles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.donte.profiles.model.Userapp;

@Repository							
public interface UserRepository extends JpaRepository<Userapp, Long>{
	Userapp findByLogin(String login);
}
