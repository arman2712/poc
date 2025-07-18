package com.example.poc.service;

import com.example.poc.model.User;
import com.example.poc.model.UserData;
import org.springframework.stereotype.Component;

/** This class is used to map the UserData to User. */
@Component
public class PocMapper {

  /**
   * Converts UserData to User object.
   *
   * @param userData the UserData object to convert
   * @return a new User object with id, name, and last name from the provided UserData
   */
  public User toUser(UserData userData) {
    return new User(userData.getId(), userData.getName(), userData.getLastName());
  }
}
