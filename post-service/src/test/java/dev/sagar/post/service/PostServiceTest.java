package dev.sagar.post.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import dev.sagar.post.model.Post;
import dev.sagar.post.repository.PostRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Captor
    private ArgumentCaptor<Integer> integerCaptor;

    @InjectMocks
    private PostService postService;

    @Test
    public void testGetAllPosts_whenRepositoryReturnsPosts_thenReturnPosts() {
        // given
        List<Post> expectedPosts = Arrays.asList(new Post(1, "Title 1", "Body 1", 1),
                new Post(2, "Title 2", "Body 2", 2));
        when(postRepository.findAll()).thenReturn(expectedPosts);

        // when
        List<Post> actualPosts = postService.getAllPosts();

        // then
        assertEquals(expectedPosts, actualPosts);
        verify(postRepository, times(1)).findAll();
    }

    @Test
    public void testGetPostById_whenRepositoryReturnsPost_thenReturnPost() {
        // given
        Post expectedPost = new Post(1, "Title 1", "Body 1", 1);
        when(postRepository.findById(eq(1))).thenReturn(Optional.of(expectedPost));

        // when
        Optional<Post> actualPost = postService.getPostById(1);

        // then
        assertTrue(actualPost.isPresent());
        assertEquals(expectedPost, actualPost.get());
        verify(postRepository, times(1)).findById(eq(1));
    }

    @Test
    public void testGetPostById_whenRepositoryReturnsEmpty_thenReturnEmptyOptional() {
        // given
        when(postRepository.findById(eq(1))).thenReturn(Optional.empty());

        // when
        Optional<Post> actualPost = postService.getPostById(1);

        // then
        assertFalse(actualPost.isPresent());
        verify(postRepository, times(1)).findById(eq(1));
    }

    @Test
    public void testGetPostsByUserId_whenRepositoryReturnsPosts_thenReturnPosts() {
        // given
        List<Post> expectedPosts = Arrays.asList(new Post(1, "Title 1", "Body 1", 1),
                new Post(2, "Title 2", "Body 2", 1));
        when(postRepository.findByUserId(eq(1))).thenReturn(expectedPosts);

        // when
        List<Post> actualPosts = postService.getPostsByUserId(1);

        // then
        assertEquals(expectedPosts, actualPosts);
        verify(postRepository, times(1)).findByUserId(eq(1));
    }
}