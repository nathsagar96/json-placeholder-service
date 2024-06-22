package dev.sagar.user.service;

import dev.sagar.user.model.User;
import dev.sagar.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        logger.debug("Retrieving all users");

        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        logger.debug("Retrieving user with id: {}", id);

        return userRepository.findById(id);
    }

    public User createUser(User user) {
        logger.debug("Creating user: {}", user);

        return userRepository.save(user);
    }

    public User updateUser(User user) {
        logger.debug("Updating user: {}", user);

        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        logger.debug("Deleting user: {}", user);

        userRepository.delete(user);
    }
}