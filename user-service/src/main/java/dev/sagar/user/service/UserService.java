package dev.sagar.user.service;

import dev.sagar.user.model.User;
import dev.sagar.user.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  public Optional<User> getUserById(Integer id) {
    return userRepository.findById(id);
  }
}
