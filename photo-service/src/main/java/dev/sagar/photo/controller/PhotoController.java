package dev.sagar.photo.controller;

import dev.sagar.photo.exception.PhotoNotFoundException;
import dev.sagar.photo.model.Photo;
import dev.sagar.photo.service.PhotoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/photos")
@Validated
public class PhotoController {

    private static final Logger logger = LoggerFactory.getLogger(PhotoController.class);

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping
    public List<Photo> getAllPhotos() {
        logger.info("Fetching all photos");

        return photoService.getAllPhotos();
    }

    @GetMapping("/{id}")
    public Photo getPhotoById(@PathVariable Long id) {
        logger.info("Fetching photo with id: {}", id);

        return photoService.getPhotoById(id)
                .orElseThrow(() -> new PhotoNotFoundException("Photo not found with id: " + id));
    }

    @PostMapping
    public Photo createPhoto(@Valid @RequestBody Photo photo) {
        logger.info("Creating photo: {}", photo);

        return photoService.createPhoto(photo);
    }

    @PutMapping("/{id}")
    public Photo updatePhoto(@PathVariable Long id, @Valid @RequestBody Photo photoDetails) {
        logger.info("Updating photo with id: {}", id);

        Photo photo = photoService.getPhotoById(id)
                .orElseThrow(() -> new PhotoNotFoundException("Photo not found with id: " + id));

        photo.setAlbumId(photoDetails.getAlbumId());
        photo.setTitle(photoDetails.getTitle());
        photo.setUrl(photoDetails.getUrl());
        photo.setThumbnailUrl(photoDetails.getThumbnailUrl());

        return photoService.updatePhoto(photo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhoto(@PathVariable Long id) {
        logger.info("Deleting photo with id: {}", id);

        Photo photo = photoService.getPhotoById(id)
                .orElseThrow(() -> new PhotoNotFoundException("Photo not found with id: " + id));

        photoService.deletePhoto(photo);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/album/{albumId}")
    public List<Photo> getPhotosByAlbumId(@PathVariable Long albumId) {
        logger.info("Fetching photos by album id: {}", albumId);

        return photoService.getPhotosByAlbumId(albumId);
    }
}