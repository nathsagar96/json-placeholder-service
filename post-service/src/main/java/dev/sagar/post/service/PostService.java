package dev.sagar.post.service;

import dev.sagar.post.model.Post;
import dev.sagar.post.repository.PostRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
  private final PostRepository postRepository;

  public List<Post> getPosts() {
    return postRepository.findAll();
  }

  public Optional<Post> getPostById(Integer id) {
    return postRepository.findById(id);
  }

  public List<Post> getPostsByUserId(Integer userId) {
    return postRepository.findByUserId(userId);
  }
}
