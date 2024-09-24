package dev.sagar.photo.service;

import dev.sagar.photo.model.Photo;
import dev.sagar.photo.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "photos")
public class PhotoService {

    private static final Logger logger = LoggerFactory.getLogger(PhotoService.class);
    private final PhotoRepository photoRepository;

    @Cacheable
    public List<Photo> getPhotos() {
        logger.info("Fetching all photos");
        return photoRepository.findAll();
    }

    @Cacheable(key = "#id")
    public Optional<Photo> getPhotoById(Integer id) {
        logger.info("Fetching photo by id: {}", id);
        return photoRepository.findById(id);
    }

    @Cacheable(key = "#albumId")
    public List<Photo> getPhotosByAlbumId(Integer albumId) {
        logger.info("Fetching photos by album id: {}", albumId);
        return photoRepository.findByAlbumId(albumId);
    }
}
