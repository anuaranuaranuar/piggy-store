package com.anuar.piggy_store.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.anuar.piggy_store.domain.User;
import com.anuar.piggy_store.repository.UserRespository;
import com.anuar.piggy_store.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRespository userRespository;

    public SecurityFilter(TokenService tokenService, UserRespository userRespository) {
        this.tokenService = tokenService;
        this.userRespository = userRespository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String tokenJWT = getToken(request);
        if (StringUtils.hasText(tokenJWT)) {
            String subject = tokenService.getSubject(tokenJWT);
            User user = (User) userRespository.findByEmail(subject);

            var authentication = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    public String getToken(HttpServletRequest request) {

        String authorizationHeader = request.getHeader("Authorization");

        if (StringUtils.hasText(authorizationHeader)) {
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;
    }

}
