package dev.sagar.album.service;

import dev.sagar.album.model.Album;
import dev.sagar.album.repository.AlbumRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AlbumServiceTest {

    @Mock
    private AlbumRepository albumRepository;

    @InjectMocks
    private AlbumService albumService;

    private Album sampleAlbum;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sampleAlbum = new Album();
        sampleAlbum.setId(1L);
        sampleAlbum.setUserId(1L);
        sampleAlbum.setTitle("Test Album");
    }

    @Test
    void testGetAllAlbums() {
        List<Album> albums = new ArrayList<>();
        albums.add(sampleAlbum);

        when(albumRepository.findAll()).thenReturn(albums);

        List<Album> fetchedAlbums = albumService.getAllAlbums();

        assertEquals(1, fetchedAlbums.size());
        assertEquals("Test Album", fetchedAlbums.get(0).getTitle());
    }

    @Test
    void testGetAlbumById() {
        when(albumRepository.findById(1L)).thenReturn(Optional.of(sampleAlbum));

        Optional<Album> fetchedAlbumOptional = albumService.getAlbumById(1L);

        assertTrue(fetchedAlbumOptional.isPresent());
        assertEquals("Test Album", fetchedAlbumOptional.get().getTitle());
    }

    @Test
    void testCreateAlbum() {
        when(albumRepository.save(sampleAlbum)).thenReturn(sampleAlbum);

        Album createdAlbum = albumService.createAlbum(sampleAlbum);

        assertEquals("Test Album", createdAlbum.getTitle());
    }

    @Test
    void testUpdateAlbum() {
        when(albumRepository.save(sampleAlbum)).thenReturn(sampleAlbum);

        Album updatedAlbum = albumService.updateAlbum(sampleAlbum);

        assertEquals("Test Album", updatedAlbum.getTitle());
    }

    @Test
    void testDeleteAlbum() {
        albumService.deleteAlbum(sampleAlbum);

        verify(albumRepository, times(1)).delete(sampleAlbum);
    }

    @Test
    void testGetAlbumsByUserId() {
        List<Album> albums = new ArrayList<>();
        albums.add(sampleAlbum);

        when(albumRepository.findByUserId(1L)).thenReturn(albums);

        List<Album> fetchedAlbums = albumService.getAlbumsByUserId(1L);

        assertEquals(1, fetchedAlbums.size());
        assertEquals("Test Album", fetchedAlbums.get(0).getTitle());
    }
}