package com.example.poc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PokemonResponse(
    List<PokemonAbilities> abilities,
    @JsonProperty("base_experience") int baseExperience,
    PokemonCries cries,
    List<PokemonForm> forms,
    @JsonProperty("game_indices") List<GameIndices> gameIndices,
    Integer height) {}
