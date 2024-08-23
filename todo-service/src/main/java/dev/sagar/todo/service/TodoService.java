package dev.sagar.todo.service;

import dev.sagar.todo.model.Todo;
import dev.sagar.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private static final Logger logger = LoggerFactory.getLogger(TodoService.class);
    private final TodoRepository todoRepository;

    public List<Todo> getTodos() {
        logger.info("Fetching all todos");
        return todoRepository.findAll();
    }

    public Optional<Todo> getTodoById(Integer id) {
        logger.info("Fetching todo by id: {}", id);
        return todoRepository.findById(id);
    }

    public List<Todo> getTodosByUserId(Integer userId) {
        logger.info("Fetching todos by user id: {}", userId);
        return todoRepository.findByUserId(userId);
    }
}
