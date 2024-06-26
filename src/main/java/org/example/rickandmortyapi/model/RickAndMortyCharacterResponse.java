package org.example.rickandmortyapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RickAndMortyCharacterResponse {
    private List<RickAndMortyCharacter> results;
    private RickAndMortyCharacterInfo info;
}
