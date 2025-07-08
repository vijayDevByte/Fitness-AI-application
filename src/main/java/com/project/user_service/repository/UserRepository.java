package com.project.user_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.user_service.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {

    Optional<UserModel> findByEmail(String email);

    boolean existsByEmail(String email);

}