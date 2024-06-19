package dev.sagar.post.controller;

import dev.sagar.post.exception.GlobalExceptionHandler;
import dev.sagar.post.model.Post;
import dev.sagar.post.service.PostService;
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

public class PostControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(postController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    public void testGetAllPosts() throws Exception {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post());
        when(postService.getAllPosts()).thenReturn(posts);

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(postService, times(1)).getAllPosts();
    }

    @Test
    public void testGetPostById() throws Exception {
        Post post = new Post();
        post.setId(1L);
        when(postService.getPostById(1L)).thenReturn(Optional.of(post));

        mockMvc.perform(get("/posts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(postService, times(1)).getPostById(1L);
    }

    @Test
    public void testGetPostById_NotFound() throws Exception {
        when(postService.getPostById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/posts/1"))
                .andExpect(status().isNotFound());

        verify(postService, times(1)).getPostById(1L);
    }

    @Test
    public void testCreatePost() throws Exception {
        Post post = new Post();
        post.setTitle("New Post");
        when(postService.createPost(any(Post.class))).thenReturn(post);

        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"New Post\",\"body\":\"This is a new post.\",\"userId\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Post"));

        verify(postService, times(1)).createPost(any(Post.class));
    }

    @Test
    public void testUpdatePost() throws Exception {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Updated Post");
        when(postService.getPostById(1L)).thenReturn(Optional.of(post));
        when(postService.updatePost(any(Post.class))).thenReturn(post);

        mockMvc.perform(put("/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Post\",\"body\":\"This is an updated post.\",\"userId\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Post"));

        verify(postService, times(1)).getPostById(1L);
        verify(postService, times(1)).updatePost(any(Post.class));
    }

    @Test
    public void testUpdatePost_NotFound() throws Exception {
        when(postService.getPostById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Post\",\"body\":\"This is an updated post.\",\"userId\":1}"))
                .andExpect(status().isNotFound());

        verify(postService, times(1)).getPostById(1L);
        verify(postService, never()).updatePost(any(Post.class));
    }

    @Test
    public void testDeletePost() throws Exception {
        Post post = new Post();
        post.setId(1L);
        when(postService.getPostById(1L)).thenReturn(Optional.of(post));

        mockMvc.perform(delete("/posts/1"))
                .andExpect(status().isNoContent());

        verify(postService, times(1)).getPostById(1L);
        verify(postService, times(1)).deletePost(post);
    }

    @Test
    public void testDeletePost_NotFound() throws Exception {
        when(postService.getPostById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/posts/1"))
                .andExpect(status().isNotFound());


        verify(postService, times(1)).getPostById(1L);
        verify(postService, never()).deletePost(any(Post.class));
    }

    @Test
    public void testGetPostsByUserId() throws Exception {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post());
        when(postService.getPostsByUserId(1L)).thenReturn(posts);

        mockMvc.perform(get("/posts/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(postService, times(1)).getPostsByUserId(1L);
    }
}