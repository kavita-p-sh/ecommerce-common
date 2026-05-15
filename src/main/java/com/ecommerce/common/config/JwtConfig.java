package com.ecommerce.common.config;

import io.jsonwebtoken.io.Decoders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Provides separate JWT secret keys for user tokens and service-to-service tokens.
 */
@Configuration
public class JwtConfig {

    @Value("${jwt.user.secret:${jwt.secret}}")
    private String userSecret;

    @Value("${jwt.service.secret:${jwt.secret}}")
    private String serviceSecret;


    /**
     * Converts user JWT secret into SecretKey.
     */
    @Bean
    public SecretKey userSecretKey() {
        return createSecretKey(userSecret);
    }

    /**
     * Converts service JWT secret into SecretKey.
     */
    @Bean
    public SecretKey serviceSecretKey() {
        return createSecretKey(serviceSecret);
    }

    private SecretKey createSecretKey(String secret) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return new SecretKeySpec(keyBytes, "AES");
    }
}
