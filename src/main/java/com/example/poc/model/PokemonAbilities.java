package com.example.poc.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PokemonAbilities(
    PokemonAbility ability, @JsonProperty("is_hidden") boolean isHidden, int slot) {}
