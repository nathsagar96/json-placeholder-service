package dev.sagar.comment.service;

import dev.sagar.comment.model.Comment;
import dev.sagar.comment.repository.CommentRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
  private final CommentRepository repository;

  public List<Comment> getComments() {
    return repository.findAll();
  }

  public Optional<Comment> getCommentById(Integer id) {
    return repository.findById(id);
  }

  public List<Comment> getCommentsByPostId(Integer postId) {
    return repository.findByPostId(postId);
  }
}
