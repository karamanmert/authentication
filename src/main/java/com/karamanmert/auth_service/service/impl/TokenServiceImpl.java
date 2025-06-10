package com.karamanmert.auth_service.service.impl;

import com.karamanmert.auth_service.entity.AuthUser;
import com.karamanmert.auth_service.entity.Token;
import com.karamanmert.auth_service.repository.TokenRepository;
import com.karamanmert.auth_service.service.spec.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author karamanmert
 */
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenRepository repository;

    @Override
    public void update(Token token) {
        Token tokenToBeUpdated = repository.findById(token.getId()).orElseThrow(() -> new RuntimeException("test"));
        repository.save(tokenToBeUpdated);
    }

    @Override
    public void delete(Token token) {
        Token tokenToBeUpdated = repository.findById(token.getId()).orElseThrow(() -> new RuntimeException("test"));
        repository.delete(tokenToBeUpdated);
    }

    @Override
    public void deleteByAccessToken(String token) {
        Optional<Token> tokenByAccessToken = repository.findTokenByAccessToken(token);


        if (tokenByAccessToken.isEmpty()) {
            throw new RuntimeException("Token not found");
        }

        repository.delete(tokenByAccessToken.get());
    }

    @Override
    public Token save(Token token) {
        return repository.save(token);
    }
}
