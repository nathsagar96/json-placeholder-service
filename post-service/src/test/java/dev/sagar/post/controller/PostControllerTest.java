package dev.sagar.post.controller;

import dev.sagar.post.model.Post;
import dev.sagar.post.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        postRepository.deleteAll();
    }

    @Test
    void testGetAllPosts() throws Exception {
        Post post1 = new Post();
        post1.setUserId(1L);
        post1.setTitle("First Post");
        post1.setBody("This is the first post");

        Post post2 = new Post();
        post2.setUserId(1L);
        post2.setTitle("Second Post");
        post2.setBody("This is the second post");

        postRepository.saveAll(Arrays.asList(post1, post2));

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("First Post")))
                .andExpect(jsonPath("$[1].title", is("Second Post")));
    }

    @Test
    void testGetPostById() throws Exception {
        Post post = new Post();
        post.setUserId(1L);
        post.setTitle("Single Post");
        post.setBody("This is a single post");
        Post savedPost = postRepository.save(post);

        mockMvc.perform(get("/posts/{id}", savedPost.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Single Post")));
    }

    @Test
    public void testGetPostByIdNotFound() throws Exception {
        mockMvc.perform(get("/posts/100")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message").value("Post not found with id: 100"));
    }

    @Test
    void testCreatePost() throws Exception {
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":1, \"title\":\"New Post\", \"body\":\"This is a new post\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("New Post")));
    }

    @Test
    public void testCreatePostValidation() throws Exception {
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"userId\": 1, \"title\": \"\", \"body\": \"Sample body\" }"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdatePost() throws Exception {
        Post post = new Post();
        post.setUserId(1L);
        post.setTitle("Old Post");
        post.setBody("This is the old post");
        Post savedPost = postRepository.save(post);

        mockMvc.perform(put("/posts/{id}", savedPost.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":1, \"title\":\"Updated Post\", \"body\":\"This is the updated post\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Updated Post")));
    }

    @Test
    void testDeletePost() throws Exception {
        Post post = new Post();
        post.setUserId(1L);
        post.setTitle("Delete Post");
        post.setBody("This post will be deleted");
        Post savedPost = postRepository.save(post);

        mockMvc.perform(delete("/posts/{id}", savedPost.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetPostsByUserId() throws Exception {
        Post post1 = new Post();
        post1.setUserId(1L);
        post1.setTitle("User Post 1");
        post1.setBody("This is the first post by user 1");

        Post post2 = new Post();
        post2.setUserId(1L);
        post2.setTitle("User Post 2");
        post2.setBody("This is the second post by user 1");

        postRepository.saveAll(Arrays.asList(post1, post2));

        mockMvc.perform(get("/posts/user/{userId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("User Post 1")))
                .andExpect(jsonPath("$[1].title", is("User Post 2")));
    }
}