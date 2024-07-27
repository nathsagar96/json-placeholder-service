package dev.sagar.post.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.sagar.post.model.Post;
import dev.sagar.post.repository.PostRepository;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * DataLoader class is responsible for loading initial data into the application. It implements the
 * CommandLineRunner interface, which allows it to run specific code when the Spring Boot
 * application starts.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

  /** ObjectMapper instance for JSON deserialization. */
  private final ObjectMapper objectMapper;

  /** PostRepository instance for saving posts to the database. */
  private final PostRepository postRepository;

  /**
   * This method is called when the Spring Boot application starts. It reads a JSON file containing
   * a list of posts, deserializes it into a List of Post objects, and saves them to the database
   * using the PostRepository.
   *
   * @param args Command line arguments. Not used in this method.
   */
  @Override
  public void run(String... args) {
    try {
      TypeReference<List<Post>> typeReference = new TypeReference<>() {};
      InputStream inputStream = getClass().getResourceAsStream("/data/posts.json");
      List<Post> posts = objectMapper.readValue(inputStream, typeReference);
      postRepository.saveAll(posts);
    } catch (IOException ioException) {
      log.error("Unable to load posts", ioException);
    }
  }
}
