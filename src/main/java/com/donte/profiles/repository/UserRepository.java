package com.donte.profiles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.donte.profiles.model.UserEntity;

@Repository							
public interface UserRepository extends JpaRepository<UserEntity, Long>{
	UserEntity findByLogin(String login);
}
