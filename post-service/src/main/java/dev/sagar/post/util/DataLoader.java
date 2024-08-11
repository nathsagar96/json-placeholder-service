package dev.sagar.post.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.sagar.post.model.Post;
import dev.sagar.post.repository.PostRepository;
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
    private final PostRepository postRepository;

    @Override
    public void run(String... args) {
        try {
            TypeReference<List<Post>> typeReference = new TypeReference<>() {
            };
            InputStream inputStream = getClass().getResourceAsStream("/data/posts.json");
            List<Post> posts = objectMapper.readValue(inputStream, typeReference);
            postRepository.saveAll(posts);
        } catch (IOException ioException) {
            log.error("Unable to load posts", ioException);
        }
    }
}
