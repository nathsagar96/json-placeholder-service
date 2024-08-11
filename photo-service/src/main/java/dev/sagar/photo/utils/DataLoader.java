package dev.sagar.photo.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.sagar.photo.model.Photo;
import dev.sagar.photo.repository.PhotoRepository;
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
    private final PhotoRepository photoRepository;

    @Override
    public void run(String... args) {
        try {
            TypeReference<List<Photo>> typeReference = new TypeReference<>() {
            };
            InputStream inputStream = getClass().getResourceAsStream("/data/photos.json");
            List<Photo> photos = objectMapper.readValue(inputStream, typeReference);
            photoRepository.saveAll(photos);
        } catch (IOException ioException) {
            log.error("Unable to load photos", ioException);
        }
    }
}
