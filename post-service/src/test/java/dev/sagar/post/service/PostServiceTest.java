package dev.sagar.post.service;

import dev.sagar.post.model.Post;
import dev.sagar.post.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPosts() {
        Post post1 = new Post();
        post1.setId(1L);
        Post post2 = new Post();
        post2.setId(2L);

        when(postRepository.findAll()).thenReturn(Arrays.asList(post1, post2));

        List<Post> posts = postService.getAllPosts();
        assertEquals(2, posts.size());
        verify(postRepository, times(1)).findAll();
    }

    @Test
    void testGetPostById() {
        Post post = new Post();
        post.setId(1L);

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));

        Optional<Post> foundPost = postService.getPostById(1L);
        assertTrue(foundPost.isPresent());
        assertEquals(1L, foundPost.get().getId());
        verify(postRepository, times(1)).findById(1L);
    }

    @Test
    void testCreatePost() {
        Post post = new Post();
        post.setTitle("New Post");

        when(postRepository.save(post)).thenReturn(post);

        Post createdPost = postService.createPost(post);
        assertNotNull(createdPost);
        assertEquals("New Post", createdPost.getTitle());
        verify(postRepository, times(1)).save(post);
    }

    @Test
    void testUpdatePost() {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Updated Post");

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(postRepository.save(post)).thenReturn(post);

        Post postDetails = new Post();
        postDetails.setTitle("Updated Post");

        Post updatedPost = postService.updatePost(1L, postDetails);
        assertNotNull(updatedPost);
        assertEquals("Updated Post", updatedPost.getTitle());
        verify(postRepository, times(1)).findById(1L);
        verify(postRepository, times(1)).save(post);
    }

    @Test
    void testDeletePost() {
        Post post = new Post();
        post.setId(1L);

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));

        postService.deletePost(1L);
        verify(postRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetPostsByUserId() {
        Post post1 = new Post();
        post1.setUserId(1L);
        Post post2 = new Post();
        post2.setUserId(1L);

        when(postRepository.findByUserId(1L)).thenReturn(Arrays.asList(post1, post2));

        List<Post> posts = postService.getPostsByUserId(1L);
        assertEquals(2, posts.size());
        verify(postRepository, times(1)).findByUserId(1L);
    }
}