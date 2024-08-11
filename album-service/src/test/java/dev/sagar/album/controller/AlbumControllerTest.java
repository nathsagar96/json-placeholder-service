package dev.sagar.album.controller;

import dev.sagar.album.exception.AlbumNotFoundException;
import dev.sagar.album.model.Album;
import dev.sagar.album.service.AlbumService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlbumControllerTest {

    @Mock
    private AlbumService albumService;

    @InjectMocks
    private AlbumController albumController;

    @Test
    void testGetAlbums_whenServiceReturnsAlbums_thenReturnAlbums() {
        // given
        List<Album> albums = List
                .of(new Album(1, "Title 1", 1),
                        new Album(2, "Title 2", 2));
        when(albumService.getAlbums()).thenReturn(albums);

        // when
        List<Album> result = albumController.getAlbums();

        // then
        assertEquals(albums, result);
    }

    @Test
    void testGetAlbumById_whenServiceReturnsAlbum_thenReturnAlbum() {
        // given
        Album album = new Album(1, "Title 1", 1);
        when(albumService.getAlbumById(eq(1))).thenReturn(Optional.of(album));

        // when
        Album result = albumController.getAlbumById(1);

        // then
        assertEquals(album, result);
    }

    @Test
    public void testGetAlbumById_throwsAlbumNotFoundException_whenAlbumNotFound() {
        // given
        when(albumService.getAlbumById(eq(1))).thenReturn(Optional.empty());

        // when
        assertThrows(AlbumNotFoundException.class, () -> albumController.getAlbumById(1));
    }

    @Test
    void testCreateAlbum_returnsCreatedAlbum() {
        // given
        Album album = new Album(101, "Title 1", 1);
        Album createdAlbum = new Album(101, "Title 1", 1);

        // when
        Album result = albumController.createAlbum(album);

        // then
        assertEquals(result.getId(), createdAlbum.getId());
    }

    @Test
    void testUpdateAlbum_returnsUpdatedAlbum() {
        // given
        Album album = new Album(1, "Title 1", 1);
        Album updatedAlbum = new Album(1, "Title 2", 1);
        when(albumService.getAlbumById(eq(1))).thenReturn(Optional.of(album));

        // when
        Album result = albumController.updateAlbum(1, updatedAlbum);

        // then
        assertEquals(result.getTitle(), updatedAlbum.getTitle());
        assertEquals(result.getUserId(), updatedAlbum.getUserId());
    }

    @Test
    public void testUpdateAlbum_throwsAlbumNotFoundException_whenAlbumNotFound() {
        // given
        Album updatedAlbum = new Album(1, "Title 2", 1);
        when(albumService.getAlbumById(eq(1))).thenReturn(Optional.empty());

        // when
        assertThrows(AlbumNotFoundException.class, () -> albumController.updateAlbum(1, updatedAlbum));
    }

    @Test
    void testGetAlbumsByUserId_whenServiceReturnsAlbums_thenReturnAlbums() {
        // given
        List<Album> albums = List
                .of(new Album(1, "Title 1", 1),
                        new Album(2, "Title 2", 1));
        when(albumService.getAlbumsByUserId(eq(1))).thenReturn(albums);

        // when
        List<Album> result = albumController.getAlbumsByUserId(1);

        // then
        assertEquals(albums, result);
    }
}
