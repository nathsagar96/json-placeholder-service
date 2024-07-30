package dev.sagar.user.controller;

import dev.sagar.user.exception.UserNotFoundException;
import dev.sagar.user.model.User;
import dev.sagar.user.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserService userService;

  @GetMapping
  public List<User> getUsers() {
    return userService.getUsers();
  }

  @GetMapping("/{id}")
  public User getUserById(@PathVariable Integer id) {
    return userService
        .getUserById(id)
        .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
  }

  @PostMapping
  public User createUser(@RequestBody User user) {
    user.setId(11);
    return user;
  }

  @PutMapping("/{id}")
  public User updateUser(@PathVariable Integer id, @RequestBody User userDetails) {
    User user =
        userService
            .getUserById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

    user.setId(id);
    user.setName(userDetails.getName());
    user.setUsername(userDetails.getUsername());
    user.setEmail(userDetails.getEmail());
    user.setAddress(userDetails.getAddress());
    user.setPhone(userDetails.getPhone());
    user.setWebsite(userDetails.getWebsite());
    user.setCompany(userDetails.getCompany());
    return user;
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteUser() {}
}
