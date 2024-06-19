package dev.sagar.comment.service;

import dev.sagar.comment.model.Comment;
import dev.sagar.comment.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllComments() {
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment());
        when(commentRepository.findAll()).thenReturn(comments);

        List<Comment> result = commentService.getAllComments();
        assertEquals(1, result.size());
        verify(commentRepository, times(1)).findAll();
    }

    @Test
    public void testGetCommentById() {
        Comment comment = new Comment();
        comment.setId(1L);
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        Optional<Comment> result = commentService.getCommentById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(commentRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateComment() {
        Comment comment = new Comment();
        comment.setBody("Test comment");
        when(commentRepository.save(comment)).thenReturn(comment);

        Comment result = commentService.createComment(comment);
        assertNotNull(result);
        assertEquals("Test comment", result.getBody());
        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    public void testUpdateComment() {
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setBody("Updated comment");
        when(commentRepository.save(comment)).thenReturn(comment);

        Comment result = commentService.updateComment(comment);
        assertNotNull(result);
        assertEquals("Updated comment", result.getBody());
        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    public void testDeleteComment() {
        Comment comment = new Comment();
        comment.setId(1L);

        doNothing().when(commentRepository).delete(comment);

        commentService.deleteComment(comment);
        verify(commentRepository, times(1)).delete(comment);
    }

    @Test
    public void testGetCommentsByPostId() {
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment());
        when(commentRepository.findByPostId(1L)).thenReturn(comments);

        List<Comment> result = commentService.getCommentsByPostId(1L);
        assertEquals(1, result.size());
        verify(commentRepository, times(1)).findByPostId(1L);
    }
}