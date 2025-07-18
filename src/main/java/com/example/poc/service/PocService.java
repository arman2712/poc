package com.example.poc.service;

import com.example.poc.model.User;
import com.example.poc.model.UserData;
import com.example.poc.repository.PocRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** This class is used to interact with the database. */
@Service
public class PocService {

  private final Logger log = LoggerFactory.getLogger(PocService.class);

  private final PocRepository pocRepository;
  private final PocMapper mapper;

  public PocService(PocRepository pocRepository, PocMapper mapper) {
    this.pocRepository = pocRepository;
    this.mapper = mapper;
  }

  /**
   * This method is used to get all the users.
   *
   * @return List<User>
   */
  public List<User> findUsers() {
    log.info("Getting all users");
    return pocRepository.findAll().stream().map(mapper::toUser).toList();
  }

  /**
   * This method is used to get a user by id.
   *
   * @param id id
   * @return User
   */
  public Optional<User> getUserById(long id) {
    log.info("Getting user by id: {}", id);
    Optional<UserData> userData = pocRepository.findById(id);
    return userData.map(mapper::toUser);
  }

  /**
   * This method is used to get a user by name.
   *
   * @param name name
   * @return List<User>
   */
  public List<User> getUserByName(String name) {
    log.info("Getting user by name: {}", name);
    return pocRepository.findByNameContainingIgnoreCase(name).stream().map(mapper::toUser).toList();
  }
}
