package dev.sagar.photo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import dev.sagar.photo.exception.PhotoNotFoundException;
import dev.sagar.photo.model.Photo;
import dev.sagar.photo.service.PhotoService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PhotoControllerTest {

  @Mock private PhotoService photoService;

  @InjectMocks private PhotoController photoController;

  @Test
  void testGetPhotos_whenServiceReturnsPhotos_thenReturnPhotos() {
    // given
    List<Photo> photos =
        List.of(
            new Photo(1, "title", "url", "thumbnailUrl", 1),
            new Photo(2, "title", "url", "thumbnailUrl", 1));
    when(photoService.getPhotos()).thenReturn(photos);

    // when
    List<Photo> result = photoController.getPhotos();

    // then
    assertEquals(photos, result);
  }

  @Test
  void testGetPhotoById_whenServiceReturnsPhoto_thenReturnPhoto() {
    // given
    Photo photo = new Photo(1, "title", "url", "thumbnailUrl", 1);
    when(photoService.getPhotoById(eq(1))).thenReturn(Optional.of(photo));

    // when
    Photo result = photoController.getPhotoById(1);

    // then
    assertEquals(photo, result);
  }

  @Test
  public void testGetPhotoById_throwsPhotoNotFoundException_whenPhotoNotFound() {
    // given
    when(photoService.getPhotoById(eq(1))).thenReturn(Optional.empty());

    // when
    assertThrows(PhotoNotFoundException.class, () -> photoController.getPhotoById(1));
  }

  @Test
  void testCreatePhoto_returnsCreatedPhoto() {
    // given
    Photo photo = new Photo(null, "title", "url", "thumbnailUrl", 1);
    Photo createdPhoto = new Photo(5001, "title", "url", "thumbnailUrl", 1);

    // when
    Photo result = photoController.createPhoto(photo);

    // then
    assertEquals(result.getId(), createdPhoto.getId());
  }

  @Test
  void testUpdatePhoto_returnsUpdatedPhoto() {
    // given
    Photo photo = new Photo(1, "title", "url", "thumbnailUrl", 1);
    Photo updatedPhoto = new Photo(1, "updated title", "updated url", "updated thumbnailUrl", 1);
    when(photoService.getPhotoById(eq(1))).thenReturn(Optional.of(photo));

    // when
    Photo result = photoController.updatePhoto(1, updatedPhoto);

    // then
    assertEquals(result.getTitle(), updatedPhoto.getTitle());
    assertEquals(result.getUrl(), updatedPhoto.getUrl());
    assertEquals(result.getThumbnailUrl(), updatedPhoto.getThumbnailUrl());
    assertEquals(result.getAlbumId(), updatedPhoto.getAlbumId());
  }

  @Test
  public void testUpdatePhoto_throwsPhotoNotFoundException_whenPhotoNotFound() {
    // given
    Photo updatedPhoto = new Photo(1, "updated title", "updated url", "updated thumbnailUrl", 1);
    when(photoService.getPhotoById(eq(1))).thenReturn(Optional.empty());

    // when
    assertThrows(PhotoNotFoundException.class, () -> photoController.updatePhoto(1, updatedPhoto));
  }

  @Test
  void testGetPhotosByAlbumId_whenServiceReturnsPhotos_thenReturnPhotos() {
    // given
    List<Photo> photos =
        List.of(
            new Photo(1, "title", "url", "thumbnailUrl", 1),
            new Photo(2, "title", "url", "thumbnailUrl", 1));
    when(photoService.getPhotosByAlbumId(eq(1))).thenReturn(photos);

    // when
    List<Photo> result = photoController.getPhotosByAlbumId(1);

    // then
    assertEquals(photos, result);
  }
}
