package dev.sagar.photo.controller;

import dev.sagar.photo.exception.GlobalExceptionHandler;
import dev.sagar.photo.model.Photo;
import dev.sagar.photo.service.PhotoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
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


public class PhotoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PhotoService photoService;

    @InjectMocks
    private PhotoController photoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(photoController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void testGetAllPhotos() throws Exception {
        Photo photo1 = new Photo(1L, 1L, "Sunset Beach", "http://example.com/photos/sunset-beach.jpg", "http://example.com/photos/thumbnails/sunset-beach.jpg");
        Photo photo2 = new Photo(2L, 1L, "Mountain Hike", "http://example.com/photos/mountain-hike.jpg", "http://example.com/photos/thumbnails/mountain-hike.jpg");

        when(photoService.getAllPhotos()).thenReturn(Arrays.asList(photo1, photo2));

        mockMvc.perform(get("/photos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        verify(photoService, times(1)).getAllPhotos();
    }

    @Test
    void testGetPhotoById() throws Exception {
        Photo photo = new Photo(1L, 1L, "Sunset Beach", "http://example.com/photos/sunset-beach.jpg", "http://example.com/photos/thumbnails/sunset-beach.jpg");

        when(photoService.getPhotoById(1L)).thenReturn(Optional.of(photo));

        mockMvc.perform(get("/photos/1"))
                .andExpect(status().isOk());

        verify(photoService, times(1)).getPhotoById(1L);
    }

    @Test
    void testGetPhotoById_NotFound() throws Exception {
        when(photoService.getPhotoById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/photos/1"))
                .andExpect(status().isNotFound());

        verify(photoService, times(1)).getPhotoById(1L);
    }

    @Test
    void testCreatePhoto() throws Exception {
        Photo photo = new Photo(1L, 1L, "Sunset Beach", "http://example.com/photos/sunset-beach.jpg", "http://example.com/photos/thumbnails/sunset-beach.jpg");

        when(photoService.createPhoto(any(Photo.class))).thenReturn(photo);

        mockMvc.perform(post("/photos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"albumId\":1,\"title\":\"Sunset Beach\",\"url\":\"http://example.com/photos/sunset-beach.jpg\",\"thumbnailUrl\":\"http://example.com/photos/thumbnails/sunset-beach.jpg\"}"))
                .andExpect(status().isOk());

        verify(photoService, times(1)).createPhoto(any(Photo.class));
    }

    @Test
    void testUpdatePhoto() throws Exception {
        Photo existingPhoto = new Photo(1L, 1L, "Sunset Beach", "http://example.com/photos/sunset-beach.jpg", "http://example.com/photos/thumbnails/sunset-beach.jpg");
        Photo updatedPhoto = new Photo(1L, 1L, "Mountain Hike", "http://example.com/photos/mountain-hike.jpg", "http://example.com/photos/thumbnails/mountain-hike.jpg");

        when(photoService.getPhotoById(1L)).thenReturn(Optional.of(existingPhoto));
        when(photoService.updatePhoto(any(Photo.class))).thenReturn(updatedPhoto);

        mockMvc.perform(put("/photos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"albumId\":1,\"title\":\"Mountain Hike\",\"url\":\"http://example.com/photos/mountain-hike.jpg\",\"thumbnailUrl\":\"http://example.com/photos/thumbnails/mountain-hike.jpg\"}"))
                .andExpect(status().isOk());

        verify(photoService, times(1)).getPhotoById(1L);
        verify(photoService, times(1)).updatePhoto(any(Photo.class));
    }

    @Test
    void testUpdatePhoto_NotFound() throws Exception {
        when(photoService.getPhotoById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/photos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"albumId\":1,\"title\":\"Mountain Hike\",\"url\":\"http://example.com/photos/mountain-hike.jpg\",\"thumbnailUrl\":\"http://example.com/photos/thumbnails/mountain-hike.jpg\"}"))
                .andExpect(status().isNotFound());

        verify(photoService, times(1)).getPhotoById(1L);
        verify(photoService, never()).updatePhoto(any(Photo.class));
    }

    @Test
    void testDeletePhoto() throws Exception {
        Photo photo = new Photo(1L, 1L, "Sunset Beach", "http://example.com/photos/sunset-beach.jpg", "http://example.com/photos/thumbnails/sunset-beach.jpg");

        when(photoService.getPhotoById(1L)).thenReturn(Optional.of(photo));
        doNothing().when(photoService).deletePhoto(any(Photo.class));

        mockMvc.perform(delete("/photos/1"))
                .andExpect(status().isNoContent());

        verify(photoService, times(1)).getPhotoById(1L);
        verify(photoService, times(1)).deletePhoto(any(Photo.class));
    }

    @Test
    void testDeletePhoto_NotFound() throws Exception {
        when(photoService.getPhotoById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/photos/1"))
                .andExpect(status().isNotFound());

        verify(photoService, times(1)).getPhotoById(1L);
        verify(photoService, never()).deletePhoto(any(Photo.class));
    }

    @Test
    void testGetPhotosByAlbumId() throws Exception {
        Photo photo1 = new Photo(1L, 1L, "Sunset Beach", "http://example.com/photos/sunset-beach.jpg", "http://example.com/photos/thumbnails/sunset-beach.jpg");
        Photo photo2 = new Photo(2L, 1L, "Mountain Hike", "http://example.com/photos/mountain-hike.jpg", "http://example.com/photos/thumbnails/mountain-hike.jpg");

        when(photoService.getPhotosByAlbumId(1L)).thenReturn(Arrays.asList(photo1, photo2));

        mockMvc.perform(get("/photos/album/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        verify(photoService, times(1)).getPhotosByAlbumId(1L);
    }
}