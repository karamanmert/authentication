package com.karamanmert.auth_service.service.spec;

import com.karamanmert.auth_service.entity.Token;

/**
 * @author karamanmert
 */
public interface TokenService {

    void update(Token token);

    void delete(Token token);

    void deleteByAccessToken(String token);

    Token save(Token token);
}
