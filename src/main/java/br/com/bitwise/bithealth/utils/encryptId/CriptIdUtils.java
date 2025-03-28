package br.com.bitwise.bithealth.utils.encryptId;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class CriptIdUtils {

    private Key jwtSecretKey;

    public CriptIdUtils(Key jwtSecretKey) {
        this.jwtSecretKey = jwtSecretKey;
    }

    public String decodeToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String generateTokenId(String id) {
        return Jwts.builder()
                .setSubject(id)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()))
                .signWith(jwtSecretKey, SignatureAlgorithm.HS512)
                .compact();
    }
}
