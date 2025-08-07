package com.flyingminds.sms.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

/**
 * Minimal JWT issuer for stateless auth.
 * Reads secret and issuer from configuration and issues HS256 tokens.
 */
@Service
public class JwtService {
    private final Key key;
    private final String issuer;

    public JwtService(@Value("${app.jwt-secret}") String secret,
                      @Value("${app.issuer}") String issuer) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.issuer = issuer;
    }

    public String generateToken(String subject, Map<String, Object> claims, long ttlSeconds) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(subject)
                .addClaims(claims)
                .setIssuer(issuer)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(ttlSeconds)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}