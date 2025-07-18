package com.example.poc.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GameIndices(@JsonProperty("game_index") Integer gameIndex, PokemonVersion version) {}
