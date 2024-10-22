package org.example.springbootcarservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.access-token.ttl-millis}")
    private Long accessTokenTTLMillis;
    @Value("${jwt.refresh-token.ttl-millis}")
    private Long refreshTokenTTLMillis;

    private Key key;
    private JwtParser jwtParser;

    public void setUpKey() {
        key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
    }

    private String generateToken(String email, long ttlMillis, Map<String, Object> claims) {
        return Jwts
                .builder()
                .addClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ttlMillis))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateAccessToken(UserDetails customer) {
        List<String> roles = customer
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return generateToken(customer.getUsername(), accessTokenTTLMillis, Map.of("roles", roles));
    }

    public String generateRefreshToken(UserDetails customer) {
        return generateToken(customer.getUsername(), refreshTokenTTLMillis, Collections.emptyMap());
    }

    public boolean isTokenExpired(String token) {
        Date expiredAt = extractFromToken(token, Claims::getExpiration);
        return !expiredAt.after(new Date());
    }

    public String extractUsername(String token) {
        return extractFromToken(token, Claims::getSubject);
    }

    public <T> T extractFromToken(String token, Function<Claims, T> extractor) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();
        return extractor.apply(claims);
    }
}