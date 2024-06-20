package dev.sagar.album.controller;

import dev.sagar.album.exception.AlbumNotFoundException;
import dev.sagar.album.model.Album;
import dev.sagar.album.service.AlbumService;
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
@RequestMapping("/albums")
@Validated
public class AlbumController {

    private static final Logger logger = LoggerFactory.getLogger(AlbumController.class);

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public List<Album> getAllAlbums() {
        logger.info("Fetching all albums");

        return albumService.getAllAlbums();
    }

    @GetMapping("/{id}")
    public Album getAlbumById(@PathVariable Long id) {
        logger.info("Fetching album with id: {}", id);

        return albumService.getAlbumById(id)
                .orElseThrow(() -> new AlbumNotFoundException("Album not found with id: " + id));
    }

    @PostMapping
    public Album createAlbum(@Valid @RequestBody Album album) {
        logger.info("Creating album: {}", album);
        return albumService.createAlbum(album);
    }

    @PutMapping("/{id}")
    public Album updateAlbum(@PathVariable Long id, @Valid @RequestBody Album albumDetails) {
        logger.info("Updating album: {}", id);

        Album album = albumService.getAlbumById(id)
                .orElseThrow(() -> new AlbumNotFoundException("Album not found with id: " + id));

        album.setUserId(albumDetails.getUserId());
        album.setTitle(albumDetails.getTitle());

        return albumService.updateAlbum(album);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        logger.info("Deleting album with id: {}", id);

        Album album = albumService.getAlbumById(id)
                .orElseThrow(() -> new AlbumNotFoundException("Album not found with id: " + id));

        albumService.deleteAlbum(album);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public List<Album> getAlbumsByUserId(@PathVariable Long userId) {
        logger.info("Fetching albums by user id: {}", userId);

        return albumService.getAlbumsByUserId(userId);
    }
}