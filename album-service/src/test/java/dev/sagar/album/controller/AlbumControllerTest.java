package dev.sagar.album.controller;

import dev.sagar.album.exception.GlobalExceptionHandler;
import dev.sagar.album.model.Album;
import dev.sagar.album.service.AlbumService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AlbumControllerTest {

    @Mock
    private AlbumService albumService;

    @InjectMocks
    private AlbumController albumController;

    private MockMvc mockMvc;

    private Album sampleAlbum;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(albumController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        sampleAlbum = new Album();
        sampleAlbum.setId(1L);
        sampleAlbum.setUserId(1L);
        sampleAlbum.setTitle("Test Album");
    }

    @Test
    void testGetAllAlbums() throws Exception {
        List<Album> albums = new ArrayList<>();
        albums.add(sampleAlbum);

        when(albumService.getAllAlbums()).thenReturn(albums);

        mockMvc.perform(get("/albums"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Test Album"));
    }

    @Test
    void testGetAlbumById() throws Exception {
        when(albumService.getAlbumById(1L)).thenReturn(Optional.of(sampleAlbum));

        mockMvc.perform(get("/albums/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Test Album"));
    }

    @Test
    void testGetAlbumById_NotFound() throws Exception {
        when(albumService.getAlbumById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/albums/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateAlbum() throws Exception {
        when(albumService.createAlbum(any(Album.class))).thenReturn(sampleAlbum);

        mockMvc.perform(post("/albums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":1,\"title\":\"Test Album\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.title").value("Test Album"));
    }

    @Test
    void testUpdateAlbum() throws Exception {
        when(albumService.getAlbumById(1L)).thenReturn(Optional.of(sampleAlbum));
        sampleAlbum.setTitle("Updated Album");
        when(albumService.updateAlbum(sampleAlbum)).thenReturn(sampleAlbum);

        mockMvc.perform(put("/albums/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":1,\"title\":\"Updated Album\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.title").value("Updated Album"));
    }

    @Test
    void testUpdateAlbum_NotFound() throws Exception {
        when(albumService.getAlbumById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/albums/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":1,\"title\":\"Updated Album\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteAlbum() throws Exception {
        when(albumService.getAlbumById(1L)).thenReturn(Optional.of(sampleAlbum));

        mockMvc.perform(delete("/albums/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteAlbum_NotFound() throws Exception {
        when(albumService.getAlbumById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/albums/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAlbumsByUserId() throws Exception {
        List<Album> albums = new ArrayList<>();
        albums.add(sampleAlbum);

        when(albumService.getAlbumsByUserId(1L)).thenReturn(albums);

        mockMvc.perform(get("/albums/user/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].userId").value(1))
                .andExpect(jsonPath("$[0].title").value("Test Album"));
    }
}