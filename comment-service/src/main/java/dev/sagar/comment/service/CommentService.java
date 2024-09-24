package dev.sagar.comment.service;

import dev.sagar.comment.model.Comment;
import dev.sagar.comment.repository.CommentRepository;
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
@CacheConfig(cacheNames = "comments")
public class CommentService {

    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);
    private final CommentRepository repository;

    @Cacheable
    public List<Comment> getComments() {
        logger.info("Fetching all comments");
        return repository.findAll();
    }

    @Cacheable(key = "#id")
    public Optional<Comment> getCommentById(Integer id) {
        logger.info("Fetching comment by id: {}", id);
        return repository.findById(id);
    }

    @Cacheable(key = "#postId")
    public List<Comment> getCommentsByPostId(Integer postId) {
        logger.info("Fetching comments by postId: {}", postId);
        return repository.findByPostId(postId);
    }
}
