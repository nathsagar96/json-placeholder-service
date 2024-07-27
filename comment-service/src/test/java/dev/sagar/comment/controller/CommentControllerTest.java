package dev.sagar.comment.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import dev.sagar.comment.exception.CommentNotFoundException;
import dev.sagar.comment.model.Comment;
import dev.sagar.comment.service.CommentService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CommentControllerTest {

  @Mock private CommentService commentService;

  @InjectMocks private CommentController commentController;

  @Test
  void testGetComments_whenServiceReturnsComments_thenReturnComments() {
    // given
    List<Comment> comments =
        Arrays.asList(
            new Comment(1, "Name 1", "Email 1", "Body 1", 1),
            new Comment(2, "Name 2", "Email 2", "Body 2", 2));
    when(commentService.getComments()).thenReturn(comments);

    // when
    List<Comment> result = commentController.getComments();

    // then
    assertEquals(comments, result);
  }

  @Test
  void testGetCommentById_whenServiceReturnsComment_thenReturnComment() {
    // given
    Comment comment = new Comment(1, "Name 1", "Email 1", "Body 1", 1);
    when(commentService.getCommentById(eq(1))).thenReturn(Optional.of(comment));

    // when
    Comment result = commentController.getCommentById(1);

    // then
    assertEquals(comment, result);
  }

  @Test
  public void testGetCommentById_throwsCommentNotFoundException_whenCommentNotFound() {
    // given
    when(commentService.getCommentById(eq(1))).thenReturn(Optional.empty());

    // when
    assertThrows(CommentNotFoundException.class, () -> commentController.getCommentById(1));
  }

  @Test
  void testCreateComment_returnsCreatedComment() {
    // given
    Comment comment = new Comment(null, "Name 1", "Email 1", "Body 1", 1);
    Comment createdComment = new Comment(501, "Name 1", "Email 1", "Body 1", 1);

    // when
    Comment result = commentController.createComment(comment);

    // then
    assertEquals(result.getId(), createdComment.getId());
  }

  @Test
  void testUpdateComment_returnsUpdatedComment() {
    // given
    Comment comment = new Comment(1, "Name 2", "Email 2", "Body 2", 1);
    Comment updatedComment = new Comment(1, "Name 2", "Email 2", "Body 2", 1);
    when(commentService.getCommentById(eq(1))).thenReturn(Optional.of(comment));

    // when
    Comment result = commentController.updateComment(1, updatedComment);

    // then
    assertEquals(result.getName(), updatedComment.getName());
    assertEquals(result.getBody(), updatedComment.getBody());
    assertEquals(result.getEmail(), updatedComment.getEmail());
    assertEquals(result.getPostId(), updatedComment.getPostId());
  }

  @Test
  public void testUpdateComment_throwsCommentNotFoundException_whenCommentNotFound() {
    // given
    Comment updatedComment = new Comment(1, "Name 2", "Email 2", "Body 2", 1);
    when(commentService.getCommentById(eq(1))).thenReturn(Optional.empty());

    // when
    assertThrows(
        CommentNotFoundException.class, () -> commentController.updateComment(1, updatedComment));
  }

  @Test
  void testGetCommentsByPostId_whenServiceReturnsComments_thenReturnComments() {
    // given
    List<Comment> comments =
        Arrays.asList(
            new Comment(1, "Name 1", "Email 1", "Body 1", 1),
            new Comment(2, "Name 2", "Email 2", "Body 2", 1));
    when(commentService.getCommentsByPostId(eq(1))).thenReturn(comments);

    // when
    List<Comment> result = commentController.getCommentsByPostId(1);

    // then
    assertEquals(comments, result);
  }
}
