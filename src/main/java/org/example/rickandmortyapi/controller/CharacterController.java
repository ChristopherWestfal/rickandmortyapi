package org.example.rickandmortyapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.rickandmortyapi.model.RickAndMortyCharacter;
import org.springframework.web.bind.annotation.*;
import org.example.rickandmortyapi.service.CharacterService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/characters")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping
    public List<RickAndMortyCharacter> getAllCharactersWithLessDetails() throws IOException {
        return characterService.getAllCharactersWithLessDetails();
    }

    @GetMapping("/{id}")
    public RickAndMortyCharacter getCharacterById(@PathVariable int id) throws IOException {
        return characterService.getCharacterById(id);
    }

    @GetMapping("/statusfiltered")
    public List<RickAndMortyCharacter> getFilteredList(@RequestParam String status) throws IOException {
        return characterService.getFilteredList(status);
    }

    @GetMapping("/statisticbyspecies")
    public int getStatisticBySpecies(@RequestParam String species) throws IOException {
        return characterService.getStatisticBySpecies(species);
    }
}
