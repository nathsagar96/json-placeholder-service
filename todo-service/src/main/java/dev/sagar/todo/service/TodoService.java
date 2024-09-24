package dev.sagar.todo.service;

import dev.sagar.todo.model.Todo;
import dev.sagar.todo.repository.TodoRepository;
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
@CacheConfig(cacheNames = "todos")
public class TodoService {

    private static final Logger logger = LoggerFactory.getLogger(TodoService.class);
    private final TodoRepository todoRepository;

    @Cacheable
    public List<Todo> getTodos() {
        logger.info("Fetching all todos");
        return todoRepository.findAll();
    }

    @Cacheable(key = "#id")
    public Optional<Todo> getTodoById(Integer id) {
        logger.info("Fetching todo by id: {}", id);
        return todoRepository.findById(id);
    }

    @Cacheable(key = "#userId")
    public List<Todo> getTodosByUserId(Integer userId) {
        logger.info("Fetching todos by user id: {}", userId);
        return todoRepository.findByUserId(userId);
    }
}
