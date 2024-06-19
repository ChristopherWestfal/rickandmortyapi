package org.example.rickandmortyapi.service;

import org.example.rickandmortyapi.model.RickAndMortyCharacter;
import org.example.rickandmortyapi.model.RickAndMortyCharacterResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.util.List;


@Service
public class CharacterService {

    private final RestClient restClient;

    public CharacterService(@Value("${RickAndMortyAPI}") String baseUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public List<RickAndMortyCharacter> getAllCharactersWithLessDetails() throws IOException {
        RickAndMortyCharacterResponse response = restClient.get()
                .uri("/character")
                .retrieve()
                .body(RickAndMortyCharacterResponse.class);

        if(response != null)
            return response.getResults();
        else
            throw new IOException("No Data Found");
    }

    public RickAndMortyCharacter getCharacterById(int id) throws IOException {
        RickAndMortyCharacter response = restClient.get()
                .uri("/character/"+id)
                .retrieve()
                .body(RickAndMortyCharacter.class);

        if(response != null)
            return response;
        else
            throw new IOException("No Data Found");
    }

    public List<RickAndMortyCharacter> getFilteredList(String status) throws IOException {
        RickAndMortyCharacterResponse response = restClient.get()
                .uri("/character/?status="+status)
                .retrieve()
                .body(RickAndMortyCharacterResponse.class);

        if(response != null) {
            return response.getResults();
        }
        else
            throw new IOException("No Data Found");

    }

    public int getStatisticBySpecies(String species) throws IOException {
        RickAndMortyCharacterResponse response = restClient.get()
                .uri("/character/?species="+species)
                .retrieve()
                .body(RickAndMortyCharacterResponse.class);

        if(response != null) {
            return response.getInfo().getCount();
        }
        else
            throw new IOException("No Data Found");
    }
}
