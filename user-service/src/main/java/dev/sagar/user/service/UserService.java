package dev.sagar.user.service;

import dev.sagar.user.model.User;
import dev.sagar.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "users")
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    @Cacheable
    public List<User> getUsers() {
        logger.info("Fetching all users");
        return userRepository.findAll();
    }

    @Cacheable(key = "#id")
    public Optional<User> getUserById(Integer id) {
        logger.info("Fetching user by id: {}", id);
        return userRepository.findById(id);
    }
}
