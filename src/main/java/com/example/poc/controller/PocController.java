package com.example.poc.controller;

import com.example.poc.model.PokemonResponse;
import com.example.poc.model.User;
import com.example.poc.service.EncodingService;
import com.example.poc.service.PocService;
import com.example.poc.service.PokemonService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** This class is used to handle the requests and responses. */
@RestController
@RequestMapping("/users")
public class PocController {

  private final Logger log = LoggerFactory.getLogger(PocController.class);

  private final PocService pocService;
  private final PokemonService pokemonService;
  private final EncodingService encodingService;

  public PocController(
      PocService pocService, PokemonService pokemonService, EncodingService encodingService) {
    this.pocService = pocService;
    this.pokemonService = pokemonService;
    this.encodingService = encodingService;
  }

  /**
   * This method is used to get all the users.
   *
   * @return List<User>
   */
  @GetMapping
  public List<User> getAllUsers() {
    log.info("Getting all users");
    return pocService.findUsers();
  }

  /**
   * This method is used to get a user by id.
   *
   * @param id id
   * @return User
   */
  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable long id) {
    log.info("Getting user by id: {}", id);
    Optional<User> userFound = pocService.getUserById(id);
    return userFound.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  /**
   * This method is used to get a user by name.
   *
   * @param name name
   * @return List<User>
   */
  @GetMapping("/search/{name}")
  public List<User> getUserByName(@PathVariable String name) {
    log.info("Getting user by name: {}", name);
    return pocService.getUserByName(name);
  }

  /**
   * This method is used to get a pokemon response.
   *
   * @return PokemonResponse
   */
  @GetMapping("/pokemon")
  public PokemonResponse getPokemonResponse() {
    log.info("Getting pokemon response");
    return pokemonService.getPokemonResponse();
  }

  /**
   * This method is used to encode a string.
   *
   * @param theString theString
   * @return String
   */
  @PostMapping("/encoding/{the-string}")
  public String encode(@PathVariable("the-string") String theString) {
    log.info("Encoding string: {}", theString);
    return encodingService.encode(theString);
  }
}
