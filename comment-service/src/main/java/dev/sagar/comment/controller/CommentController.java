package dev.sagar.comment.controller;

import dev.sagar.comment.exception.CommentNotFoundException;
import dev.sagar.comment.model.Comment;
import dev.sagar.comment.service.CommentService;
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
@RequestMapping(path = "/comments")
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
    private final CommentService commentService;

    @GetMapping
    public List<Comment> getComments() {
        logger.info("Getting all comments");
        return commentService.getComments();
    }

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable int id) {
        logger.info("Getting comment with id: {}", id);
        return commentService
                .getCommentById(id)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + id));
    }

    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        logger.info("Creating comment: {}", comment);
        comment.setId(501);
        return comment;
    }

    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable int id, @RequestBody Comment commentDetails) {
        logger.info("Updating comment with id: {}", id);
        Comment comment =
                commentService
                        .getCommentById(id)
                        .orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + id));

        comment.setId(id);
        comment.setName(commentDetails.getName());
        comment.setEmail(commentDetails.getEmail());
        comment.setBody(commentDetails.getBody());
        comment.setPostId(commentDetails.getPostId());

        return comment;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable int id) {
        logger.info("Deleting comment with id: {}", id);
    }

    @GetMapping("/post/{postId}")
    public List<Comment> getCommentsByPostId(@PathVariable int postId) {
        logger.info("Getting comments for post with id: {}", postId);
        return commentService.getCommentsByPostId(postId);
    }
}
