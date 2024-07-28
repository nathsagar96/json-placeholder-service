package dev.sagar.album.service;

import dev.sagar.album.model.Album;
import dev.sagar.album.repository.AlbumRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlbumService {
  private final AlbumRepository albumRepository;

  public List<Album> getAlbums() {
    return albumRepository.findAll();
  }

  public Optional<Album> getAlbumById(Integer id) {
    return albumRepository.findById(id);
  }

  public List<Album> getAlbumsByUserId(Integer userId) {
    return albumRepository.findByUserId(userId);
  }
}
