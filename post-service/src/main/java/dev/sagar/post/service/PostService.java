package dev.sagar.post.service;

import dev.sagar.post.model.Post;
import dev.sagar.post.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private static final Logger logger = LoggerFactory.getLogger(PostService.class);

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        logger.debug("Retrieving all posts");

        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long id) {
        logger.debug("Retrieving post with id {}", id);

        return postRepository.findById(id);
    }

    public Post createPost(Post post) {
        logger.debug("Creating post with title {}", post.getTitle());

        return postRepository.save(post);
    }

    public Post updatePost(Post post) {
        logger.debug("Updating post with id {}", post.getId());

        return postRepository.save(post);
    }

    public void deletePost(Post post) {
        logger.debug("Deleting post with id {}", post.getId());

        postRepository.delete(post);
    }

    public List<Post> getPostsByUserId(Long userId) {
        logger.debug("Retrieving all post with userId {}", userId);

        return postRepository.findByUserId(userId);
    }
}