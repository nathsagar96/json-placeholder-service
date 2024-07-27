package dev.sagar.post.repository;

import dev.sagar.post.model.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface represents a repository for managing {@link Post} entities. It extends Spring Data
 * JPA's {@link JpaRepository} interface, providing basic CRUD operations and additional methods for
 * querying {@link Post} entities.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

  /**
   * Finds all {@link Post} entities associated with the given user ID.
   *
   * @param userId The unique identifier of the user whose posts should be retrieved.
   * @return A list of {@link Post} entities associated with the given user ID. If no posts are
   *     found, an empty list is returned.
   */
  List<Post> findByUserId(Integer userId);
}
