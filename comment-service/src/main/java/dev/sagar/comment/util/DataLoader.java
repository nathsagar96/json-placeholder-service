package dev.sagar.comment.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.sagar.comment.model.Comment;
import dev.sagar.comment.repository.CommentRepository;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
  private final ObjectMapper objectMapper;
  private final CommentRepository commentRepository;

  @Override
  public void run(String... args) {
    try {
      TypeReference<List<Comment>> typeReference = new TypeReference<>() {};
      InputStream inputStream = getClass().getResourceAsStream("/data/comments.json");
      List<Comment> comments = objectMapper.readValue(inputStream, typeReference);
      commentRepository.saveAll(comments);
    } catch (IOException ioException) {
      log.error("Unable to load comments", ioException);
    }
  }
}
