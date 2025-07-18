package com.example.poc.integration;

import com.example.poc.model.PokemonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * This class is used to call the external endpoint and get the response. The API is: <a
 * href="https://pokeapi.co/api/v2/pokemon/ditto">...</a>
 */
@Component
public class PokeAPIClient {

  private final Logger log = LoggerFactory.getLogger(PokeAPIClient.class);

  private final WebClient webClient;

  public PokeAPIClient(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.baseUrl("https://pokeapi.co/api/v2").build();
  }

  /**
   * This method is used to call the external endpoint and get the response.
   *
   * @return PokemonResponse
   */
  public PokemonResponse getPokemonResponse() {
    log.info("Getting pokemon response from external endpoint");
    return webClient
        .get()
        .uri("/pokemon/ditto")
        .retrieve()
        .bodyToMono(PokemonResponse.class)
        .block();
  }
}
