package dev.sagar.album.repository;

import dev.sagar.album.model.Album;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
  List<Album> findByUserId(Integer userId);
}
