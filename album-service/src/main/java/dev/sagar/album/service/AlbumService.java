package dev.sagar.album.service;

import dev.sagar.album.controller.AlbumController;
import dev.sagar.album.model.Album;
import dev.sagar.album.repository.AlbumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    private static final Logger logger = LoggerFactory.getLogger(AlbumService.class);

    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<Album> getAllAlbums() {
        logger.debug("Retrieving all albums");

        return albumRepository.findAll();
    }

    public Optional<Album> getAlbumById(Long id) {
        logger.debug("Retrieving album with id: {}", id);

        return albumRepository.findById(id);
    }

    public Album createAlbum(Album album) {
        logger.debug("Creating album: {}", album);

        return albumRepository.save(album);
    }

    public Album updateAlbum(Album album) {
        logger.debug("Updating album: {}", album);

        return albumRepository.save(album);
    }

    public void deleteAlbum(Album album) {
        logger.debug("Deleting album: {}", album);

        albumRepository.delete(album);
    }

    public List<Album> getAlbumsByUserId(Long userId) {
        logger.debug("Retrieving albums by user id: {}", userId);

        return albumRepository.findByUserId(userId);
    }
}