package dev.sagar.photo.repository;

import dev.sagar.photo.model.Photo;
import io.micrometer.observation.annotation.Observed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Observed
@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    List<Photo> findByAlbumId(Integer albumId);
}
