package com.epam.homelibrary.server.TokenManager;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class TokenManager {
    private final static Key key;

    static {
        key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String encodeToken(String login) {
        return Jwts.builder().setSubject(login).signWith(key).compact();
    }

    public boolean decodeToken(String signature) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(signature);
        } catch (JwtException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
