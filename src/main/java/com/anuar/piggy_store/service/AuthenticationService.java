package com.anuar.piggy_store.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.anuar.piggy_store.domain.User;
import com.anuar.piggy_store.dto.request.UserDto;
import com.anuar.piggy_store.mapper.UserMapper;
import com.anuar.piggy_store.repository.UserRespository;

import jakarta.transaction.Transactional;

@Service
public class AuthenticationService implements UserDetailsService {
    private final UserRespository userRepository;

    public AuthenticationService(UserRespository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      
    return userRepository.findByEmail(email);     
    }

}
