package br.com.bitwise.bithealth.security;

import br.com.bitwise.bithealth.modules.user.model.Usuario;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.Instant;

public interface TokenService {

    String generateToken(Usuario user);
    DecodedJWT validateTokenDecoded(String token);
    Instant getExpirationTime();
    String generateTokenId(String id);
    String decodeToken(String token);
}
