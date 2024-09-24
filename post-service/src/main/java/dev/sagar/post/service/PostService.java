package dev.sagar.post.service;

import dev.sagar.post.model.Post;
import dev.sagar.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "posts")
public class PostService {

    private static final Logger logger = LoggerFactory.getLogger(PostService.class);
    private final PostRepository postRepository;

    @Cacheable
    public List<Post> getPosts() {
        logger.info("Fetching all posts");
        return postRepository.findAll();
    }

    @Cacheable(key = "#id")
    public Optional<Post> getPostById(Integer id) {
        logger.info("Fetching post by id: {}", id);
        return postRepository.findById(id);
    }

    @Cacheable(key = "#userId")
    public List<Post> getPostsByUserId(Integer userId) {
        logger.info("Fetching posts by user id: {}", userId);
        return postRepository.findByUserId(userId);
    }
}
