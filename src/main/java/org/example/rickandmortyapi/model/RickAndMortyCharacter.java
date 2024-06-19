package org.example.rickandmortyapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RickAndMortyCharacter {
    private int id;
    private String name;
    private String status;
    private String species;
}
