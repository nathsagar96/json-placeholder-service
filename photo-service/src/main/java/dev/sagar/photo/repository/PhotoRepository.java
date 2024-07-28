package dev.sagar.photo.repository;

import dev.sagar.photo.model.Photo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {
  List<Photo> findByAlbumId(Integer albumId);
}
