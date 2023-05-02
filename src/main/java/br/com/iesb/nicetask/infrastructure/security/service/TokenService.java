package br.com.iesb.nicetask.infrastructure.security.service;

import br.com.iesb.nicetask.infrastructure.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class TokenService {

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .claim("authorities", userDetails.getAuthorities())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(getIssuedAt())
                .setExpiration(getExpirationTime())
                .signWith(SignatureAlgorithm.HS256, "teste").compact();
    }

    public Boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = JWTUtils.getUsername(token);
        return username.equals(userDetails.getUsername()) && !JWTUtils.isTokenExpired(token);
    }

    private Date getIssuedAt() {
        LocalDateTime timeNow = LocalDateTime.now();
        ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
        return Date.from(timeNow.atZone(zoneId).toInstant());
    }
    private Date getExpirationTime() {
        LocalDateTime timeNow = LocalDateTime.now().plusHours(1);
        ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
        return Date.from(timeNow.atZone(zoneId).toInstant());
    }
}
