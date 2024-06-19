package dev.sagar.comment.controller;

import dev.sagar.comment.exception.CommentNotFoundException;
import dev.sagar.comment.model.Comment;
import dev.sagar.comment.service.CommentService;
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
@RequestMapping("/comments")
@Validated
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments() {
        logger.info("Fetching all comments");

        return commentService.getAllComments();
    }

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable Long id) {
        logger.info("Fetching comment with id {}", id);

        return commentService.getCommentById(id)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + id));
    }

    @PostMapping
    public Comment createComment(@Valid @RequestBody Comment comment) {
        logger.info("Creating new comment with body {}", comment.getBody());

        return commentService.createComment(comment);
    }

    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable Long id, @Valid @RequestBody Comment commentDetails) {
        logger.info("Updating comment with id {}", id);

        Comment comment = commentService.getCommentById(id)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + id));

        comment.setPostId(commentDetails.getPostId());
        comment.setName(commentDetails.getName());
        comment.setEmail(commentDetails.getEmail());
        comment.setBody(commentDetails.getBody());

        return commentService.updateComment(comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        logger.info("Deleting comment with id {}", id);

        Comment comment = commentService.getCommentById(id)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + id));

        commentService.deleteComment(comment);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/post/{postId}")
    public List<Comment> getCommentsByPostId(@PathVariable Long postId) {
        logger.info("Fetching all comments with postId {}", postId);

        return commentService.getCommentsByPostId(postId);
    }
}