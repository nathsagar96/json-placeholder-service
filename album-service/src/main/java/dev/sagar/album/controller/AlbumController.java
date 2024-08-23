package dev.sagar.album.controller;

import dev.sagar.album.exception.AlbumNotFoundException;
import dev.sagar.album.model.Album;
import dev.sagar.album.service.AlbumService;
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
@RequestMapping(path = "/albums")
@RequiredArgsConstructor
public class AlbumController {

    private static final Logger logger = LoggerFactory.getLogger(AlbumController.class);
    private final AlbumService albumService;

    @GetMapping
    public List<Album> getAlbums() {
        logger.info("Getting all albums");
        return albumService.getAlbums();
    }

    @GetMapping("/{id}")
    public Album getAlbumById(@PathVariable int id) {
        logger.info("Getting album with id: {}", id);
        return albumService
                .getAlbumById(id)
                .orElseThrow(() -> new AlbumNotFoundException("Album not found with id: " + id));
    }

    @PostMapping
    public Album createAlbum(@RequestBody Album album) {
        logger.info("Creating album: {}", album);
        album.setId(101);
        return album;
    }

    @PutMapping("/{id}")
    public Album updateAlbum(@PathVariable int id, @RequestBody Album albumDetails) {
        logger.info("Updating album with id: {}", id);
        Album album =
                albumService
                        .getAlbumById(id)
                        .orElseThrow(() -> new AlbumNotFoundException("Album not found with id: " + id));

        album.setId(id);
        album.setTitle(albumDetails.getTitle());
        album.setUserId(albumDetails.getUserId());
        return album;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable int id) {
        logger.info("Deleting album with id: {}", id);
    }

    @GetMapping("/user/{userId}")
    public List<Album> getAlbumsByUserId(@PathVariable int userId) {
        logger.info("Getting albums by user id: {}", userId);
        return albumService.getAlbumsByUserId(userId);
    }
}
