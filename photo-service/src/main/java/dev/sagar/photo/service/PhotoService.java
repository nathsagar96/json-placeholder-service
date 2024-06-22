package dev.sagar.photo.service;

import dev.sagar.photo.model.Photo;
import dev.sagar.photo.repository.PhotoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {

    private static final Logger logger = LoggerFactory.getLogger(PhotoService.class);

    private final PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public List<Photo> getAllPhotos() {
        logger.debug("Retrieving all photos");

        return photoRepository.findAll();
    }

    public Optional<Photo> getPhotoById(Long id) {
        logger.debug("Retrieving photo with id: {}", id);

        return photoRepository.findById(id);
    }

    public Photo createPhoto(Photo photo) {
        logger.debug("Creating photo: {}", photo);

        return photoRepository.save(photo);
    }

    public Photo updatePhoto(Photo photo) {
        logger.debug("Updating photo: {}", photo);

        return photoRepository.save(photo);
    }

    public void deletePhoto(Photo photo) {
        logger.debug("Deleting photo: {}", photo);

        photoRepository.delete(photo);
    }

    public List<Photo> getPhotosByAlbumId(Long albumId) {
        logger.debug("Retrieving photos by album id: {}", albumId);

        return photoRepository.findByAlbumId(albumId);
    }
}