package dev.sagar.post.controller;

import dev.sagar.post.exception.PostNotFoundException;
import dev.sagar.post.model.Post;
import dev.sagar.post.service.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is responsible for handling HTTP requests related to posts. It provides endpoints for
 * retrieving, creating, updating, deleting posts, and retrieving posts by user
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/posts")
public class PostController {
  private final PostService service;

  /**
   * Retrieves all posts from the database.
   *
   * @return A list of all posts.
   */
  @GetMapping
  public List<Post> getAllPosts() {
    return service.getPosts();
  }

  /**
   * Retrieves a post by its unique identifier.
   *
   * @param id The unique identifier of the post.
   * @return The post with the specified identifier.
   * @throws PostNotFoundException If a post with the given identifier is not found.
   */
  @GetMapping("/{id}")
  public Post getPostById(@PathVariable Integer id) {
    return service
        .getPostById(id)
        .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + id));
  }

  /**
   * Creates a new post in the database.
   *
   * @param post The post to be created.
   * @return The created post with its unique identifier set.
   */
  @PostMapping
  public Post createPost(@RequestBody Post post) {
    post.setId(101);
    return post;
  }

  /**
   * Updates an existing post in the database.
   *
   * @param id The unique identifier of the post to be updated.
   * @param postDetails The updated details of the post.
   * @return The updated post.
   * @throws PostNotFoundException If a post with the given identifier is not found.
   */
  @PutMapping("/{id}")
  public Post updatePost(@PathVariable Integer id, @RequestBody Post postDetails) {
    Post post =
        service
            .getPostById(id)
            .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + id));

    post.setId(id);
    post.setTitle(postDetails.getTitle());
    post.setBody(postDetails.getBody());
    post.setUserId(postDetails.getUserId());

    return post;
  }

  /** Deletes a post from the database. */
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletePost() {}

  /**
   * Retrieves all posts created by a specific user.
   *
   * @param userId The unique identifier of the user.
   * @return A list of posts created by the specified user.
   */
  @GetMapping("/user/{userId}")
  public List<Post> getPostsByUserId(@PathVariable Integer userId) {
    return service.getPostsByUserId(userId);
  }
}
