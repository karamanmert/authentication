package com.karamanmert.auth_service;

import com.karamanmert.auth_service.entity.AuthUser;
import com.karamanmert.auth_service.model.AuthUserPrincipal;
import com.karamanmert.auth_service.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@SpringBootApplication
public class AuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

	/**
	 * @author karamanmert
	 * @date 13.11.2024
	 */
}
