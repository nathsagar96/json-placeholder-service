package dev.sagar.post.controller;

import dev.sagar.post.exception.PostNotFoundException;
import dev.sagar.post.model.Post;
import dev.sagar.post.service.PostService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
@Validated
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts() {
        logger.info("Fetching all posts");

        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        logger.info("Fetching post with id {}", id);

        return postService.getPostById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + id));
    }

    @PostMapping
    public Post createPost(@Valid @RequestBody Post post) {
        logger.info("Creating new post with title {}", post.getTitle());

        return postService.createPost(post);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @Valid @RequestBody Post postDetails) {
        logger.info("Updating post with id {}", id);

        Post post = postService.getPostById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + id));

        post.setTitle(postDetails.getTitle());
        post.setBody(postDetails.getBody());
        post.setUserId(postDetails.getUserId());

        return postService.updatePost(post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        logger.info("Deleting post with id {}", id);

        Post post = postService.getPostById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + id));

        postService.deletePost(post);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public List<Post> getPostsByUserId(@PathVariable Long userId) {
        logger.info("Fetching all post with userId {}", userId);

        return postService.getPostsByUserId(userId);
    }
}