package com.training.Util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // The secret must be at least 32 characters (256 bits) for HS256 or larger for HS512
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.tokenValidity}")
    private Long tokenValidity;

    // Generate secure signing key with the secret bytes
    private Key getSigningKey() {
        // This will throw WeakKeyException if secret is too short - ensure your secret is strong enough!
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Generate JWT token using user's username and configured claims
    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Claims claims = Jwts.claims().setSubject(user.getUsername());

        long nowMillis = System.currentTimeMillis();
        Date expiryDate = new Date(nowMillis + tokenValidity);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("GreatLearning")
                .setAudience("Java Developers")
                .setExpiration(expiryDate)
                .setIssuedAt(new Date(nowMillis))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512) // Use HS512 algorithm
                .compact();
    }

    // Extract username (subject) from token after validating
    public String getUserName(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                                .setSigningKey(getSigningKey())
                                .build()
                                .parseClaimsJws(token)
                                .getBody();
            return claims.getSubject();
        } catch (JwtException e) {
            e.printStackTrace();
            // Handle token parsing exceptions (expired, malformed, unsupported, invalid)
        }
        return null;
    }

    // Validate JWT token
    public void validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
            // If no exception, token is valid
        } catch (JwtException e) {
            e.printStackTrace();
            // Custom handling: throw exceptions or log invalid token
        }
    }
}
