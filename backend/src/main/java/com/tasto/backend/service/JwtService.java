package com.tasto.backend.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.SECRET_KEY}")
    private String SECRET_KEY;
    public String generateToken(String email,String role)
    {
     return Jwts.builder()
             .setSubject(email)
             .claim("role",role)
             .setIssuedAt(new Date())
             .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
             .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
             .compact();
    }
    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
    public String extractRole(String token)
    {
        return getClaims(token).get("role", String.class);
    }
}
