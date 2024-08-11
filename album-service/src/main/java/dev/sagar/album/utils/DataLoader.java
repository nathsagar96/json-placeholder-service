package dev.sagar.album.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.sagar.album.model.Album;
import dev.sagar.album.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final ObjectMapper objectMapper;
    private final AlbumRepository albumRepository;

    @Override
    public void run(String... args) {
        try {
            TypeReference<List<Album>> typeReference = new TypeReference<>() {
            };
            InputStream inputStream = getClass().getResourceAsStream("/data/albums.json");
            List<Album> albums = objectMapper.readValue(inputStream, typeReference);
            albumRepository.saveAll(albums);
        } catch (IOException ioException) {
            log.error("Unable to load albums", ioException);
        }
    }
}
