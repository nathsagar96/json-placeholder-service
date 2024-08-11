package dev.sagar.album.service;

import dev.sagar.album.model.Album;
import dev.sagar.album.repository.AlbumRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AlbumServiceTest {

    @Mock
    private AlbumRepository albumRepository;

    @InjectMocks
    private AlbumService albumService;

    @Test
    public void testGetAlbums_whenRepositoryReturnsAlbums_thenReturnAlbums() {
        // given
        List<Album> expectedAlbums = List
                .of(new Album(1, "Title 1", 1),
                        new Album(2, "Title 2", 2));
        when(albumRepository.findAll()).thenReturn(expectedAlbums);

        // when
        List<Album> actualAlbums = albumService.getAlbums();

        // then
        assertEquals(expectedAlbums, actualAlbums);
        verify(albumRepository, times(1)).findAll();
    }

    @Test
    public void testGetAlbumById_whenRepositoryReturnsAlbum_thenReturnAlbum() {
        // given
        Album expectedAlbum = new Album(1, "Title 1", 1);
        when(albumRepository.findById(eq(1))).thenReturn(Optional.of(expectedAlbum));

        // when
        Optional<Album> actualAlbum = albumService.getAlbumById(1);

        // then
        assertTrue(actualAlbum.isPresent());
        assertEquals(expectedAlbum, actualAlbum.get());
        verify(albumRepository, times(1)).findById(eq(1));
    }

    @Test
    public void testGetAlbumById_whenRepositoryReturnsEmpty_thenReturnEmptyOptional() {
        // given
        when(albumRepository.findById(eq(1))).thenReturn(Optional.empty());

        // when
        Optional<Album> actualAlbum = albumService.getAlbumById(1);

        // then
        assertFalse(actualAlbum.isPresent());
        verify(albumRepository, times(1)).findById(eq(1));
    }

    @Test
    public void testGetAlbumsByUserId_whenRepositoryReturnsAlbums_thenReturnAlbums() {
        // given
        List<Album> expectedAlbums = List
                .of(new Album(1, "Title 1", 1),
                        new Album(1, "Title 1", 1));
        when(albumRepository.findByUserId(eq(1))).thenReturn(expectedAlbums);

        // when
        List<Album> actualAlbums = albumService.getAlbumsByUserId(1);

        // then
        assertEquals(expectedAlbums, actualAlbums);
        verify(albumRepository, times(1)).findByUserId(eq(1));
    }
}
