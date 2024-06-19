package dev.sagar.comment.service;

import dev.sagar.comment.model.Comment;
import dev.sagar.comment.repository.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllComments() {
        logger.debug("Retrieving all comments");

        return commentRepository.findAll();
    }

    public Optional<Comment> getCommentById(Long id) {
        logger.debug("Retrieving comment with id {}", id);

        return commentRepository.findById(id);
    }

    public Comment createComment(Comment comment) {
        logger.debug("Creating comment with body {}", comment.getBody());

        return commentRepository.save(comment);
    }

    public Comment updateComment(Comment comment) {
        logger.debug("Updating comment with id {}", comment.getId());

        return commentRepository.save(comment);
    }

    public void deleteComment(Comment comment) {
        logger.debug("Deleting comment with id {}", comment.getId());

        commentRepository.delete(comment);
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        logger.debug("Retrieving all comments with postId {}", postId);

        return commentRepository.findByPostId(postId);
    }
}
