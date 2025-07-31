package com.anuar.piggy_store.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anuar.piggy_store.domain.User;
import com.anuar.piggy_store.dto.request.UserDto;
import com.anuar.piggy_store.dto.response.DatosToken;
import com.anuar.piggy_store.service.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    final private AuthenticationManager manager;
    final private TokenService tokenService;
    
    public AuthenticationController(AuthenticationManager manager, TokenService tokenService) {
		this.manager = manager;
		this.tokenService = tokenService;
	}





	@PostMapping
    public ResponseEntity<DatosToken> signUp(@RequestBody @Valid UserDto userDto) {
        // crea un objecto que puede ser comparado con authentication
        var token = new UsernamePasswordAuthenticationToken(userDto.email(), userDto.password());
        // verifica que el usuario exista en la db
        Authentication authentication = manager.authenticate(token); 
        // crea el token
        var tokenJWT= tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new DatosToken(tokenJWT));
    }
}
