package co.com.personalsoft.jwtexample.security.keys;

import co.com.personalsoft.jwtexample.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${example.app.jwtSecret}")
    private String jwtSecret;

    @Value("${example.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(UserDetailsImpl userPrincipal) {
        return generateTokenFromUsername(userPrincipal.getUsername());
    }

    public String generateTokenFromUsername(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            LOGGER.error("Firma del token Invalida: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            LOGGER.error("Token Invalido: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            LOGGER.error("Token ha expirado: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            LOGGER.error("Token ha sido alterado: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.error("Token esta vacío: {}", e.getMessage());
        }
        return false;
    }

}
