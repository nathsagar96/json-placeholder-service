package dev.sagar.comment.controller;

import dev.sagar.comment.exception.CommentNotFoundException;
import dev.sagar.comment.model.Comment;
import dev.sagar.comment.service.CommentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
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

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/comments")
public class CommentController {

  private final CommentService commentService;

  @GetMapping
  public List<Comment> getComments() {
    return commentService.getComments();
  }

  @GetMapping("/{id}")
  public Comment getCommentById(@PathVariable Integer id) {
    return commentService
        .getCommentById(id)
        .orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + id));
  }

  @PostMapping
  public Comment createComment(@RequestBody Comment comment) {
    comment.setId(501);
    return comment;
  }

  @PutMapping("/{id}")
  public Comment updateComment(@PathVariable Integer id, @RequestBody Comment commentDetails) {
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
  public void deleteComment() {}

  @GetMapping("/post/{postId}")
  public List<Comment> getCommentsByPostId(@PathVariable Integer postId) {
    return commentService.getCommentsByPostId(postId);
  }
}
