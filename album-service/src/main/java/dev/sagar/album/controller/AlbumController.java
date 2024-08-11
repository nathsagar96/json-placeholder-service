package dev.sagar.album.controller;

import dev.sagar.album.exception.AlbumNotFoundException;
import dev.sagar.album.model.Album;
import dev.sagar.album.service.AlbumService;
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

import java.util.List;

@RestController
@RequestMapping(path = "/albums")
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;

    @GetMapping
    public List<Album> getAlbums() {
        return albumService.getAlbums();
    }

    @GetMapping("/{id}")
    public Album getAlbumById(@PathVariable Integer id) {
        return albumService
                .getAlbumById(id)
                .orElseThrow(() -> new AlbumNotFoundException("Album not found with id: " + id));
    }

    @PostMapping
    public Album createAlbum(@RequestBody Album album) {
        album.setId(101);
        return album;
    }

    @PutMapping("/{id}")
    public Album updateAlbum(@PathVariable Integer id, @RequestBody Album albumDetails) {
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
    public void deleteAlbum() {
    }

    @GetMapping("/user/{userId}")
    public List<Album> getAlbumsByUserId(@PathVariable Integer userId) {
        return albumService.getAlbumsByUserId(userId);
    }
}
