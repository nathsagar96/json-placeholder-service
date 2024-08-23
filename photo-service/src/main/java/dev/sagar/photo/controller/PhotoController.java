package dev.sagar.photo.controller;

import dev.sagar.photo.exception.PhotoNotFoundException;
import dev.sagar.photo.model.Photo;
import dev.sagar.photo.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/photos")
public class PhotoController {

    private static final Logger logger = LoggerFactory.getLogger(PhotoController.class);
    private final PhotoService photoService;

    @GetMapping
    public List<Photo> getPhotos() {
        logger.info("Getting all photos");
        return photoService.getPhotos();
    }

    @GetMapping("/{id}")
    public Photo getPhotoById(@PathVariable int id) {
        logger.info("Getting photo with id: {}", id);
        return photoService
                .getPhotoById(id)
                .orElseThrow(() -> new PhotoNotFoundException("Photo not found with id: " + id));
    }

    @PostMapping
    public Photo createPhoto(@RequestBody Photo photo) {
        logger.info("Creating photo: {}", photo);
        photo.setId(5001);
        return photo;
    }

    @PutMapping("/{id}")
    public Photo updatePhoto(@PathVariable int id, @RequestBody Photo photoDetails) {
        logger.info("Updating photo with id: {}", id);
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
    public void deletePhoto(@PathVariable int id) {
        logger.info("Deleting photo with id: {}", id);
    }

    @GetMapping("/album/{albumId}")
    public List<Photo> getPhotosByAlbumId(@PathVariable int albumId) {
        logger.info("Getting photos for album with id: {}", albumId);
        return photoService.getPhotosByAlbumId(albumId);
    }
}
