package org.example.rickandmortyapi.service;

import org.example.rickandmortyapi.model.RickAndMortyCharacter;
import org.example.rickandmortyapi.model.RickAndMortyCharacterResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class CharacterService {

    private final RestClient restClient;

    public CharacterService() {
        this.restClient = RestClient.builder()
                .baseUrl("https://rickandmortyapi.com/api")
                .build();
    }

    public List<RickAndMortyCharacter> getAllCharactersWithLessDetails() throws IOException {
        RickAndMortyCharacterResponse response = getCharacterResponse();

        if(response != null)
            return response.getResults();
        else
            throw new IOException("No Data Found");
    }

    public RickAndMortyCharacter getCharacterById(int id) throws IOException {
        RickAndMortyCharacterResponse response = getCharacterResponse();

        if(response != null) {
            List<RickAndMortyCharacter> results = response.getResults();

            for(RickAndMortyCharacter c : results)
                if(c.getId() == id)
                    return c;

            return null;
        }
        else
            throw new IOException("No Data Found");
    }

    public List<RickAndMortyCharacter> getFilteredList(String status) throws IOException {
        RickAndMortyCharacterResponse response = getCharacterResponse();
        List<RickAndMortyCharacter> filteredResults = new ArrayList<>();

        if(response != null) {
            List<RickAndMortyCharacter> results = response.getResults();

            for(RickAndMortyCharacter c : results)
                if(c.getStatus().equalsIgnoreCase(status))
                    filteredResults.add(c);

            return filteredResults;
        }
        else
            throw new IOException("No Data Found");

    }

    private RickAndMortyCharacterResponse getCharacterResponse(){

        return restClient.get()
                .uri("/character")
                .retrieve()
                .body(RickAndMortyCharacterResponse.class);
    }

    public int getStatisticBySpecies(String species) throws IOException {
        RickAndMortyCharacterResponse response = getCharacterResponse();

        if(response != null) {
            List<RickAndMortyCharacter> results = response.getResults();
            int counter = 0;

            for(RickAndMortyCharacter c : results)
                if(c.getSpecies().equalsIgnoreCase(species))
                    counter++;

            return counter;
        }
        else
            throw new IOException("No Data Found");
    }
}
