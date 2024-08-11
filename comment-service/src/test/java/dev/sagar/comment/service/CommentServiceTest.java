package dev.sagar.comment.service;

import dev.sagar.comment.model.Comment;
import dev.sagar.comment.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    @Test
    public void testGetComments_whenRepositoryReturnsComments_thenReturnComments() {
        // given
        List<Comment> expectedComments = List
                .of(new Comment(1, "Name 1", "Email 1", "Body 1", 1),
                        new Comment(2, "Name 2", "Email 2", "Body 2", 2));
        when(commentRepository.findAll()).thenReturn(expectedComments);

        // when
        List<Comment> actualComments = commentService.getComments();

        // then
        assertEquals(expectedComments, actualComments);
        verify(commentRepository, times(1)).findAll();
    }

    @Test
    public void testGetCommentById_whenRepositoryReturnsComment_thenReturnComment() {
        // given
        Comment expectedComment = new Comment(1, "Name 1", "Email 1", "Body 1", 1);
        when(commentRepository.findById(eq(1))).thenReturn(Optional.of(expectedComment));

        // when
        Optional<Comment> actualComment = commentService.getCommentById(1);

        // then
        assertTrue(actualComment.isPresent());
        assertEquals(expectedComment, actualComment.get());
        verify(commentRepository, times(1)).findById(eq(1));
    }

    @Test
    public void testGetCommentById_whenRepositoryReturnsEmpty_thenReturnEmptyOptional() {
        // given
        when(commentRepository.findById(eq(1))).thenReturn(Optional.empty());

        // when
        Optional<Comment> actualComment = commentService.getCommentById(1);

        // then
        assertFalse(actualComment.isPresent());
        verify(commentRepository, times(1)).findById(eq(1));
    }

    @Test
    public void testGetCommentsByPostId_whenRepositoryReturnsComments_thenReturnComments() {
        // given
        List<Comment> expectedComments = List
                .of(new Comment(1, "Name 1", "Email 1", "Body 1", 1),
                        new Comment(2, "Name 2", "Email 2", "Body 2", 1));
        when(commentRepository.findByPostId(eq(1))).thenReturn(expectedComments);

        // when
        List<Comment> actualComments = commentService.getCommentsByPostId(1);

        // then
        assertEquals(expectedComments, actualComments);
        verify(commentRepository, times(1)).findByPostId(eq(1));
    }
}
