package dev.sagar.photo.service;

import dev.sagar.photo.model.Photo;
import dev.sagar.photo.repository.PhotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PhotoServiceTest {

    @Mock
    private PhotoRepository photoRepository;

    @InjectMocks
    private PhotoService photoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPhotos() {
        Photo photo1 = new Photo(1L, 1L, "Sunset Beach", "http://example.com/photos/sunset-beach.jpg", "http://example.com/photos/thumbnails/sunset-beach.jpg");
        Photo photo2 = new Photo(2L, 1L, "Mountain Hike", "http://example.com/photos/mountain-hike.jpg", "http://example.com/photos/thumbnails/mountain-hike.jpg");

        when(photoRepository.findAll()).thenReturn(Arrays.asList(photo1, photo2));

        List<Photo> photos = photoService.getAllPhotos();
        assertEquals(2, photos.size());
        verify(photoRepository, times(1)).findAll();
    }

    @Test
    void testGetPhotoById() {
        Photo photo = new Photo(1L, 1L, "Sunset Beach", "http://example.com/photos/sunset-beach.jpg", "http://example.com/photos/thumbnails/sunset-beach.jpg");

        when(photoRepository.findById(1L)).thenReturn(Optional.of(photo));

        Optional<Photo> foundPhoto = photoService.getPhotoById(1L);
        assertTrue(foundPhoto.isPresent());
        assertEquals(photo, foundPhoto.get());
        verify(photoRepository, times(1)).findById(1L);
    }

    @Test
    void testCreatePhoto() {
        Photo photo = new Photo(1L, 1L, "Sunset Beach", "http://example.com/photos/sunset-beach.jpg", "http://example.com/photos/thumbnails/sunset-beach.jpg");

        when(photoRepository.save(photo)).thenReturn(photo);

        Photo createdPhoto = photoService.createPhoto(photo);
        assertEquals(photo, createdPhoto);
        verify(photoRepository, times(1)).save(photo);
    }

    @Test
    void testUpdatePhoto() {
        Photo photo = new Photo(1L, 1L, "Sunset Beach", "http://example.com/photos/sunset-beach.jpg", "http://example.com/photos/thumbnails/sunset-beach.jpg");

        when(photoRepository.save(photo)).thenReturn(photo);

        Photo updatedPhoto = photoService.updatePhoto(photo);
        assertEquals(photo, updatedPhoto);
        verify(photoRepository, times(1)).save(photo);
    }

    @Test
    void testDeletePhoto() {
        Photo photo = new Photo(1L, 1L, "Sunset Beach", "http://example.com/photos/sunset-beach.jpg", "http://example.com/photos/thumbnails/sunset-beach.jpg");

        doNothing().when(photoRepository).delete(photo);

        photoService.deletePhoto(photo);
        verify(photoRepository, times(1)).delete(photo);
    }

    @Test
    void testGetPhotosByAlbumId() {
        Photo photo1 = new Photo(1L, 1L, "Sunset Beach", "http://example.com/photos/sunset-beach.jpg", "http://example.com/photos/thumbnails/sunset-beach.jpg");
        Photo photo2 = new Photo(2L, 1L, "Mountain Hike", "http://example.com/photos/mountain-hike.jpg", "http://example.com/photos/thumbnails/mountain-hike.jpg");

        when(photoRepository.findByAlbumId(1L)).thenReturn(Arrays.asList(photo1, photo2));

        List<Photo> photos = photoService.getPhotosByAlbumId(1L);
        assertEquals(2, photos.size());
        verify(photoRepository, times(1)).findByAlbumId(1L);
    }
}