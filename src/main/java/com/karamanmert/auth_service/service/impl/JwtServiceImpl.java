package com.karamanmert.auth_service.service.impl;

import com.karamanmert.auth_service.model.dto.AuthUserDetailsDto;
import com.karamanmert.auth_service.model.response.TokenResponse;
import com.karamanmert.auth_service.service.spec.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author karamanmert
 * @date 11.11.2024
 */
@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Override
    public TokenResponse generateToken(AuthUserDetailsDto userDetails) {
        Map<String, Object> claims = new HashMap<>();
        String token = this.createToken(claims, userDetails);

        return TokenResponse.builder()
                .accessToken(token)
                .build();
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claimsResolver.apply(claims);
    }

    private String createToken(Map<String, Object> claims, AuthUserDetailsDto userDetails) {
        setClaims(claims, userDetails);

        final String username = claims.get("email").toString();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .and()
                .signWith(getSignKey())
                .compact();
    }

    private void setClaims(Map<String, Object> claims, AuthUserDetailsDto userDetails) {
        claims.put("name", userDetails.name());
        claims.put("email", userDetails.email());
        claims.put("role", userDetails.role());
    }

    private SecretKey getSignKey() {
        byte[] bytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
