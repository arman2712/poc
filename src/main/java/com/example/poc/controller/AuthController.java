package com.example.poc.controller;

import com.example.poc.model.AuthLoginRequest;
import com.example.poc.security.JwtUtil;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** This class is used to authenticate the user and generate a JWT token. */
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final Logger log = LoggerFactory.getLogger(AuthController.class);

  private final String USER_NAME = "admin";
  private final String PASSWORD = "password";

  private final JwtUtil jwtUtil;

  public AuthController(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  /**
   * This method is used to authenticate the user and generate a JWT token.
   *
   * @param authLoginRequest authLoginRequest
   * @return ResponseEntity<?>
   */
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody AuthLoginRequest authLoginRequest) {
    log.info("Generating token for user: {}", authLoginRequest.getUsername());

    // Perform authentication logic here
    // For simplicity, let's assume a hardcoded username and password
    // and generate a JWT token if the credentials are valid
    if (USER_NAME.equals(authLoginRequest.getUsername())
        && PASSWORD.equals(authLoginRequest.getPassword())) {
      String role = "ADMIN"; // You can fetch the user's role from the database
      String token = jwtUtil.generateToken(authLoginRequest.getUsername(), role);

      log.info("Token generated successfully for user: {}", authLoginRequest.getUsername());

      Map<String, Object> response = new HashMap<>();
      response.put("token", token);
      response.put("username", authLoginRequest.getUsername());
      response.put("role", role);

      return ResponseEntity.ok(response);
    }
    return ResponseEntity.badRequest().body("Invalid credentials");
  }
}
