package dev.sagar.post.repository;

import dev.sagar.post.model.Post;
import io.micrometer.observation.annotation.Observed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Observed
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUserId(Integer userId);
}
