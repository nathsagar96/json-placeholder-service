package dev.sagar.photo.service;

import dev.sagar.photo.model.Photo;
import dev.sagar.photo.repository.PhotoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PhotoServiceTest {

    @Mock
    private PhotoRepository photoRepository;

    @InjectMocks
    private PhotoService photoService;

    @Test
    public void testGetPhotos_whenRepositoryReturnsPhotos_thenReturnPhotos() {
        // given
        List<Photo> expectedPhotos = List
                .of(new Photo(1, "title", "url", "thumbnailUrl", 1),
                        new Photo(2, "title", "url", "thumbnailUrl", 1));
        when(photoRepository.findAll()).thenReturn(expectedPhotos);

        // when
        List<Photo> actualPhotos = photoService.getPhotos();

        // then
        assertEquals(expectedPhotos, actualPhotos);
        verify(photoRepository, times(1)).findAll();
    }

    @Test
    public void testGetPhotoById_whenRepositoryReturnsPhoto_thenReturnPhoto() {
        // given
        Photo expectedPhoto = new Photo(1, "title", "url", "thumbnailUrl", 1);
        when(photoRepository.findById(eq(1))).thenReturn(Optional.of(expectedPhoto));

        // when
        Optional<Photo> actualPhoto = photoService.getPhotoById(1);

        // then
        assertTrue(actualPhoto.isPresent());
        assertEquals(expectedPhoto, actualPhoto.get());
        verify(photoRepository, times(1)).findById(eq(1));
    }

    @Test
    public void testGetPhotoById_whenRepositoryReturnsEmpty_thenReturnEmptyOptional() {
        // given
        when(photoRepository.findById(eq(1))).thenReturn(Optional.empty());

        // when
        Optional<Photo> actualPhoto = photoService.getPhotoById(1);

        // then
        assertFalse(actualPhoto.isPresent());
        verify(photoRepository, times(1)).findById(eq(1));
    }

    @Test
    public void testGetPhotosByAlbumId_whenRepositoryReturnsPhotos_thenReturnPhotos() {
        // given
        List<Photo> expectedPhotos = List
                .of(new Photo(1, "title", "url", "thumbnailUrl", 1),
                        new Photo(2, "title", "url", "thumbnailUrl", 1));
        when(photoRepository.findByAlbumId(eq(1))).thenReturn(expectedPhotos);

        // when
        List<Photo> actualPhotos = photoService.getPhotosByAlbumId(1);

        // then
        assertEquals(expectedPhotos, actualPhotos);
        verify(photoRepository, times(1)).findByAlbumId(eq(1));
    }
}
