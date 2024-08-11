package dev.sagar.todo.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.sagar.todo.model.Todo;
import dev.sagar.todo.repository.TodoRepository;
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
    private final TodoRepository todoRepository;

    @Override
    public void run(String... args) {
        try {
            TypeReference<List<Todo>> typeReference = new TypeReference<>() {
            };
            InputStream inputStream = getClass().getResourceAsStream("/data/todos.json");
            List<Todo> todos = objectMapper.readValue(inputStream, typeReference);
            todoRepository.saveAll(todos);
        } catch (IOException ioException) {
            log.error("Unable to load todos", ioException);
        }
    }
}
