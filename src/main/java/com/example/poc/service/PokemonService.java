package com.example.poc.service;

import com.example.poc.integration.PokeAPIClient;
import com.example.poc.model.PokemonResponse;
import org.springframework.stereotype.Service;

/** This class is used to interact with the Pokemon API. */
@Service
public class PokemonService {

  private final PokeAPIClient pokeAPIClient;

  public PokemonService(PokeAPIClient pokeAPIClient) {
    this.pokeAPIClient = pokeAPIClient;
  }

  /**
   * This method is used to get a PokemonResponse.
   *
   * @return PokemonResponse
   */
  public PokemonResponse getPokemonResponse() {
    return pokeAPIClient.getPokemonResponse();
  }
}
