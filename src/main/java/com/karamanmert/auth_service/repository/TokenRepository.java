package com.karamanmert.auth_service.repository;

import com.karamanmert.auth_service.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author karamanmert
 */
@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findTokenByAccessToken(String token);
}
