package com.example.poc.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import java.util.Date;
import org.springframework.stereotype.Component;

/** This class is used to generate and validate the JWT token. */
@Component
public class JwtUtil {

  private final String SECRET = "mySecretKey";
  private final long EXPIRATION_TIME = 86400000; // 24 hours

  /**
   * This method is used to generate the JWT token.
   *
   * @param username username
   * @param role role
   * @return String
   */
  public String generateToken(String username, String role) {
    return JWT.create()
        .withSubject(username)
        .withClaim("role", role)
        .withIssuedAt(new Date())
        .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .sign(Algorithm.HMAC256(SECRET));
  }

  public String getUsernameFromToken(String token) {
    try {
      DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
      return jwt.getSubject();
    } catch (JWTVerificationException e) {
      return null;
    }
  }

  public String getRoleFromToken(String token) {
    try {
      DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
      return jwt.getClaim("role").asString();
    } catch (JWTVerificationException e) {
      return null;
    }
  }

  public boolean validateToken(String token) {
    try {
      JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
      verifier.verify(token);
      return true;
    } catch (JWTVerificationException e) {
      return false;
    }
  }
}
