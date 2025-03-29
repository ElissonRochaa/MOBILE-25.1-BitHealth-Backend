package br.com.bitwise.bithealth.security;

import br.com.bitwise.bithealth.modules.user.exceptions.UserGenerateTokenException;
import br.com.bitwise.bithealth.modules.user.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Usuario user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(Date.from(getExpirationTime()))
                    .withClaim("tipoUsuario", List.of(user.getTipoUsuario().toString()))
                    .withClaim("id", List.of(user.getId().toString()))
                    .withClaim("nome", List.of(user.getNome()))
                    .withClaim("sobrenome", List.of(user.getSobrenome()))
                    .withClaim("ativo", List.of(user.getAtivo().toString()))
                    .withClaim("cpf", List.of(user.getCpf()))
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new UserGenerateTokenException(user.getNome());
        }
    }

    public DecodedJWT validateTokenDecoded(String token) {
        token = token.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256(secret);

        try {
            return JWT.require(algorithm)
                    .build()
                    .verify(token);
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    private Instant getExpirationTime() {
        return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.of("-03:00"));
    }

    public String generateTokenId(String id) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("Id-crip-api")
                    .withSubject(id)
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new UserGenerateTokenException(id);
        }
    }

    public String decodeToken(String token) {
        token = token.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256(secret);

        try {
            return JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }
}
