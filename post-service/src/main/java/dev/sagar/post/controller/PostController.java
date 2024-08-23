package dev.sagar.post.controller;

import dev.sagar.post.exception.PostNotFoundException;
import dev.sagar.post.model.Post;
import dev.sagar.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/posts")
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    @GetMapping
    public List<Post> getAllPosts() {
        logger.info("Getting all posts");
        return postService.getPosts();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable int id) {
        logger.info("Getting post with id: {}", id);
        return postService
                .getPostById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + id));
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        logger.info("Creating post: {}", post);
        post.setId(101);
        return post;
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable int id, @RequestBody Post postDetails) {
        logger.info("Updating post with id: {}", id);
        Post post =
                postService
                        .getPostById(id)
                        .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + id));

        post.setId(id);
        post.setTitle(postDetails.getTitle());
        post.setBody(postDetails.getBody());
        post.setUserId(postDetails.getUserId());

        return post;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable int id) {
        logger.info("Deleting post with id: {}", id);
    }

    @GetMapping("/user/{userId}")
    public List<Post> getPostsByUserId(@PathVariable int userId) {
        logger.info("Getting posts by user id: {}", userId);
        return postService.getPostsByUserId(userId);
    }
}
