package com.anuar.piggy_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.security.core.userdetails.UserDetails;

import com.anuar.piggy_store.domain.User;

public interface UserRespository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

    UserDetails findByEmail(String email);

    
}
