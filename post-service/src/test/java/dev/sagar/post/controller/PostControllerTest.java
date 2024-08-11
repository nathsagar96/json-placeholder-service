package dev.sagar.post.controller;

import dev.sagar.post.exception.PostNotFoundException;
import dev.sagar.post.model.Post;
import dev.sagar.post.service.PostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostControllerTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    @Test
    public void testGetAllPosts_returnsAllPosts() {
        // given
        List<Post> posts = List
                .of(new Post(1, "Title 1", "Body 1", 1),
                        new Post(2, "Title 2", "Body 2", 2));
        when(postService.getPosts()).thenReturn(posts);

        // when
        List<Post> result = postController.getAllPosts();

        // then
        assertEquals(posts, result);
    }

    @Test
    public void testGetPostById_returnsPostById() {
        // given
        Post post = new Post(1, "Title 1", "Body 1", 1);
        when(postService.getPostById(eq(1))).thenReturn(Optional.of(post));

        // when
        Post result = postController.getPostById(1);

        // then
        assertEquals(post, result);
    }

    @Test
    public void testGetPostById_throwsPostNotFoundException_whenPostNotFound() {
        // given
        when(postService.getPostById(eq(1))).thenReturn(Optional.empty());

        // when
        assertThrows(PostNotFoundException.class, () -> postController.getPostById(1));
    }

    @Test
    public void testCreatePost_returnsCreatedPost() {
        // given
        Post post = new Post(null, "Title 1", "Body 1", 1);
        Post createdPost = new Post(101, "Title 1", "Body 1", 1);

        // when
        Post result = postController.createPost(post);

        // then
        assertEquals(result.getId(), createdPost.getId());
    }

    @Test
    public void testUpdatePost_returnsUpdatedPost() {
        // given
        Post post = new Post(1, "Title 1", "Body 1", 1);
        Post updatedPost = new Post(1, "Title 2", "Body 2", 2);
        when(postService.getPostById(eq(1))).thenReturn(Optional.of(post));

        // when
        Post result = postController.updatePost(1, updatedPost);

        // then
        assertEquals(result.getTitle(), updatedPost.getTitle());
        assertEquals(result.getBody(), updatedPost.getBody());
        assertEquals(result.getUserId(), updatedPost.getUserId());
    }

    @Test
    public void testUpdatePost_throwsPostNotFoundException_whenPostNotFound() {
        // given
        Post updatedPost = new Post(1, "Title 2", "Body 2", 2);
        when(postService.getPostById(eq(1))).thenReturn(Optional.empty());

        // when
        assertThrows(PostNotFoundException.class, () -> postController.updatePost(1, updatedPost));
    }

    @Test
    public void testGetPostsByUserId_returnsPostsByUserId() {
        // given
        List<Post> posts = List
                .of(new Post(1, "Title 1", "Body 1", 1),
                        new Post(2, "Title 2", "Body 2", 1));
        when(postService.getPostsByUserId(eq(1))).thenReturn(posts);

        // when
        List<Post> result = postController.getPostsByUserId(1);

        // then
        assertEquals(posts, result);
    }
}
