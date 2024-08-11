package dev.sagar.photo.service;

import dev.sagar.photo.model.Photo;
import dev.sagar.photo.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhotoService {
    private final PhotoRepository photoRepository;

    public List<Photo> getPhotos() {
        return photoRepository.findAll();
    }

    public Optional<Photo> getPhotoById(Integer id) {
        return photoRepository.findById(id);
    }

    public List<Photo> getPhotosByAlbumId(Integer albumId) {
        return photoRepository.findByAlbumId(albumId);
    }
}
