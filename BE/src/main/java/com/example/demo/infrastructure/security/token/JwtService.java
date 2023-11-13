package com.example.demo.infrastructure.security.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import io.jsonwebtoken.io.Decoders;

@Service
public class JwtService {

        private static final String SECRET_KEY = "77397A244326462948404D635166546A576E5A7234753778214125442A472D4B";
        private static final List<String> blacklistedTokens = new ArrayList<>();

        public String extractUsername(String token) {
            return extractClaim(token, Claims::getSubject);
        }

        public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
            final Claims claims = extractAllClaims(token);
            return claimsResolver.apply(claims);
        }

        public String generateToken(UserDetails userDetails) {
            return generateToken(Map.of(), userDetails);
        }

        public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
            String token = Jwts
                    .builder()
                    .setClaims(extraClaims)
                    .setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact();

            return token;
        }

        public boolean isTokenValid(String token, UserDetails userDetails) {
            final String username = extractUsername(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token) && !isTokenBlacklisted(token));
        }

        private boolean isTokenExpired(String token) {
            return extractExpiration(token).before(new Date());
        }

        private Date extractExpiration(String token) {
            return extractClaim(token, Claims::getExpiration);
        }

        private Claims extractAllClaims(String token) {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }

        private Key getSignInKey() {
            byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
            return Keys.hmacShaKeyFor(keyBytes);
        }

        public void addToBlacklist(String token) {
            blacklistedTokens.add(token);
        }

        public boolean isTokenBlacklisted(String token) {
            return blacklistedTokens.contains(token);
        }

        public void removeFromBlacklist(String token) {
            blacklistedTokens.remove(token);
        }
}
