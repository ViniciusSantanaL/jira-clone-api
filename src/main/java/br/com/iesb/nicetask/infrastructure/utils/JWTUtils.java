package br.com.iesb.nicetask.infrastructure.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.function.Function;

public class JWTUtils {
    private JWTUtils() {}
    public static String getUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public static Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private static <T> T extractClaim(String token, Function<Claims, T> cliamsResolver) {
        final Claims claims = extractAllClaims(token);
        return cliamsResolver.apply(claims);
    }

    private static Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey("teste").parseClaimsJws(token).getBody();
    }

}
