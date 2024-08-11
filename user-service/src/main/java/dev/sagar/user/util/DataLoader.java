package dev.sagar.user.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.sagar.user.model.User;
import dev.sagar.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        try {
            TypeReference<List<User>> typeReference = new TypeReference<>() {
            };
            InputStream inputStream = getClass().getResourceAsStream("/data/users.json");
            List<User> users = objectMapper.readValue(inputStream, typeReference);
            userRepository.saveAll(users);
        } catch (IOException ioException) {
            log.error("Unable to load users", ioException);
        }
    }
}
