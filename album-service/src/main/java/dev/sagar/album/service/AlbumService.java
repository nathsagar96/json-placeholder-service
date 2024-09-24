package dev.sagar.album.service;

import dev.sagar.album.model.Album;
import dev.sagar.album.repository.AlbumRepository;
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
@CacheConfig(cacheNames = "albums")
public class AlbumService {

    private static final Logger logger = LoggerFactory.getLogger(AlbumService.class);
    private final AlbumRepository albumRepository;

    @Cacheable
    public List<Album> getAlbums() {
        logger.info("Fetching all albums");
        return albumRepository.findAll();
    }

    @Cacheable(key = "#id")
    public Optional<Album> getAlbumById(Integer id) {
        logger.info("Fetching album by id: {}", id);
        return albumRepository.findById(id);
    }

    @Cacheable(key = "#userId")
    public List<Album> getAlbumsByUserId(Integer userId) {
        logger.info("Fetching albums by user id: {}", userId);
        return albumRepository.findByUserId(userId);
    }
}
