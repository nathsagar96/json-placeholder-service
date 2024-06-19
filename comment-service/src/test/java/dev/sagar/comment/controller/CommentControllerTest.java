package dev.sagar.comment.controller;

import dev.sagar.comment.exception.GlobalExceptionHandler;
import dev.sagar.comment.model.Comment;
import dev.sagar.comment.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CommentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(commentController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    public void testGetAllComments() throws Exception {
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment());
        when(commentService.getAllComments()).thenReturn(comments);

        mockMvc.perform(get("/comments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(commentService, times(1)).getAllComments();
    }

    @Test
    public void testGetCommentById() throws Exception {
        Comment comment = new Comment();
        comment.setId(1L);
        when(commentService.getCommentById(1L)).thenReturn(Optional.of(comment));

        mockMvc.perform(get("/comments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(commentService, times(1)).getCommentById(1L);
    }

    @Test
    public void testGetCommentById_NotFound() throws Exception {
        when(commentService.getCommentById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/comments/1"))
                .andExpect(status().isNotFound());

        verify(commentService, times(1)).getCommentById(1L);
    }

    @Test
    public void testCreateComment() throws Exception {
        Comment comment = new Comment();
        comment.setBody("New comment");
        when(commentService.createComment(any(Comment.class))).thenReturn(comment);

        mockMvc.perform(post("/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"postId\":1,\"name\":\"John Doe\",\"email\":\"john.doe@example.com\",\"body\":\"New comment\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body").value("New comment"));

        verify(commentService, times(1)).createComment(any(Comment.class));
    }

    @Test
    public void testUpdateComment() throws Exception {
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setBody("Updated comment");
        when(commentService.getCommentById(1L)).thenReturn(Optional.of(comment));
        when(commentService.updateComment(any(Comment.class))).thenReturn(comment);

        mockMvc.perform(put("/comments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"postId\":1,\"name\":\"John Doe\",\"email\":\"john.doe@example.com\",\"body\":\"Updated comment\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body").value("Updated comment"));

        verify(commentService, times(1)).getCommentById(1L);
        verify(commentService, times(1)).updateComment(any(Comment.class));
    }

    @Test
    public void testUpdateComment_NotFound() throws Exception {
        when(commentService.getCommentById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/comments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"postId\":1,\"name\":\"John Doe\",\"email\":\"john.doe@example.com\",\"body\":\"Updated comment\"}"))
                .andExpect(status().isNotFound());

        verify(commentService, times(1)).getCommentById(1L);
        verify(commentService, never()).updateComment(any(Comment.class));
    }

    @Test
    public void testDeleteComment() throws Exception {
        Comment comment = new Comment();
        comment.setId(1L);
        when(commentService.getCommentById(1L)).thenReturn(Optional.of(comment));

        mockMvc.perform(delete("/comments/1"))
                .andExpect(status().isNoContent());

        verify(commentService, times(1)).getCommentById(1L);
        verify(commentService, times(1)).deleteComment(comment);
    }

    @Test
    public void testDeleteComment_NotFound() throws Exception {
        when(commentService.getCommentById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/comments/1"))
                .andExpect(status().isNotFound());

        verify(commentService, times(1)).getCommentById(1L);
        verify(commentService, never()).deleteComment(any(Comment.class));
    }

    @Test
    public void testGetCommentsByPostId() throws Exception {
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment());
        when(commentService.getCommentsByPostId(1L)).thenReturn(comments);

        mockMvc.perform(get("/comments/post/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(commentService, times(1)).getCommentsByPostId(1L);
    }
}