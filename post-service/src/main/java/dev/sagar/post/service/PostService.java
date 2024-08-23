package dev.sagar.post.service;

import dev.sagar.post.model.Post;
import dev.sagar.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private static final Logger logger = LoggerFactory.getLogger(PostService.class);
    private final PostRepository postRepository;

    public List<Post> getPosts() {
        logger.info("Fetching all posts");
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Integer id) {
        logger.info("Fetching post by id: {}", id);
        return postRepository.findById(id);
    }

    public List<Post> getPostsByUserId(Integer userId) {
        logger.info("Fetching posts by user id: {}", userId);
        return postRepository.findByUserId(userId);
    }
}
