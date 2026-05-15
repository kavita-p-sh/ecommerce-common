package com.ecommerce.common.security;

import com.ecommerce.common.exception.ResourceNotFoundException;
import com.ecommerce.common.util.AppConstants;
import com.ecommerce.common.util.JwtConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;


/**
 * Utility class for JWT operations like generate, validate and extract data.
 */

@Component
public class JwtUtil {

    private final SecretKey userSecretKey;
    private final long expiration;

    public JwtUtil(@Qualifier("userSecretKey") SecretKey userSecretKey,
                   @Value("${jwt.expiration}") long expiration) {
        this.userSecretKey = userSecretKey;
        this.expiration = expiration;
    }

    /**
     * Generate JWT token for user
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {

        String role = userDetails.getAuthorities()
                .stream()
                .findFirst()
                .map(object -> object.toString())
                .orElseThrow(() -> new ResourceNotFoundException(AppConstants.USER_NOT_FOUND));

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .encryptWith(userSecretKey, Jwts.KEY.DIRECT, Jwts.ENC.A256GCM)
                .compact();
    }

    /**
     * Extracts username from token.
     */
    public String extractUsername(String token) {
        Claims claims = extractClaims(token);
        return claims.getSubject();
    }


    /**
     * Extract role from token.
     * @param  token  JWT token
     * @return  user-role
     */
    public String extractRole(String token) {
        Claims claims = extractClaims(token);
        return claims.get("role", String.class);
    }


    /**
     * Validates JWT token.
     *
     * @param token JWT token
     * @param userDetails user details
     * @return true if token is valid, else false
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            Claims claims = extractClaims(token);
            String username = claims.getSubject();

            return username.equals(userDetails.getUsername())
                    && !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * Extracts all claims from token.
     */
    private Claims extractClaims(String token) {
        return Jwts.parser()
                .decryptWith(userSecretKey)
                .build()
                .parseEncryptedClaims(getToken(token))
                .getPayload();
    }


    /**
     * Removes Bearer prefix from token.
     */
    private String getToken(String token) {
        if (token != null && token.startsWith(JwtConstant.TOKEN_PREFIX)) {
            return token.substring(7);
        }
        return token;
    }
}