package dev.sagar.post.controller;

import dev.sagar.post.exception.PostNotFoundException;
import dev.sagar.post.model.Post;
import dev.sagar.post.service.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/posts")
public class PostController {
  private final PostService service;

  @GetMapping
  public List<Post> getAllPosts() {
    return service.getAllPosts();
  }

  @GetMapping("/{id}")
  public Post getPostById(@PathVariable Integer id) {
    return service
        .getPostById(id)
        .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + id));
  }

  @PostMapping
  public Post createPost(@RequestBody Post post) {
    post.setId(101);
    return post;
  }

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

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePost() {
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/user/{userId}")
  public List<Post> getPostsByUserId(@PathVariable Integer userId) {
    return service.getPostsByUserId(userId);
  }
}
