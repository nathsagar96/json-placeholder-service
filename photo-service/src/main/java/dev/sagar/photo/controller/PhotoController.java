package dev.sagar.photo.controller;

import dev.sagar.photo.exception.PhotoNotFoundException;
import dev.sagar.photo.model.Photo;
import dev.sagar.photo.service.PhotoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/photos")
public class PhotoController {

  private final PhotoService photoService;

  @GetMapping
  public List<Photo> getPhotos() {
    return photoService.getPhotos();
  }

  @GetMapping("/{id}")
  public Photo getPhotoById(@PathVariable Integer id) {
    return photoService
        .getPhotoById(id)
        .orElseThrow(() -> new PhotoNotFoundException("Photo not found with id: " + id));
  }

  @PostMapping
  public Photo createPhoto(@RequestBody Photo photo) {
    photo.setId(5001);
    return photo;
  }

  @PutMapping("/{id}")
  public Photo updatePhoto(@PathVariable Integer id, @RequestBody Photo photoDetails) {
    Photo photo =
        photoService
            .getPhotoById(id)
            .orElseThrow(() -> new PhotoNotFoundException("Photo not found with id: " + id));

    photo.setId(id);
    photo.setTitle(photoDetails.getTitle());
    photo.setUrl(photoDetails.getUrl());
    photo.setThumbnailUrl(photoDetails.getThumbnailUrl());
    photo.setAlbumId(photoDetails.getAlbumId());

    return photo;
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletePhoto() {}

  @GetMapping("/album/{albumId}")
  public List<Photo> getPhotosByAlbumId(@PathVariable Integer albumId) {
    return photoService.getPhotosByAlbumId(albumId);
  }
}
