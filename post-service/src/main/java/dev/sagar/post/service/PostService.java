package dev.sagar.post.service;

import dev.sagar.post.model.Post;
import dev.sagar.post.repository.PostRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
  private final PostRepository repository;

  /**
   * Retrieves all posts from the database.
   *
   * @return a list of all posts
   */
  public List<Post> getPosts() {
    return repository.findAll();
  }

  /**
   * Retrieves a post by its unique identifier.
   *
   * @param id the unique identifier of the post
   * @return an Optional containing the post if found, otherwise an empty Optional
   */
  public Optional<Post> getPostById(Integer id) {
    return repository.findById(id);
  }

  /**
   * Retrieves all posts created by a specific user.
   *
   * @param userId the unique identifier of the user
   * @return a list of posts created by the user
   */
  public List<Post> getPostsByUserId(Integer userId) {
    return repository.findByUserId(userId);
  }
}
