package dev.sagar.album.repository;

import dev.sagar.album.model.Album;
import io.micrometer.observation.annotation.Observed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Observed
@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
    List<Album> findByUserId(Integer userId);
}
