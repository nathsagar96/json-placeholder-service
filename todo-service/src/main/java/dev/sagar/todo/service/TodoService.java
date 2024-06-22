package dev.sagar.todo.service;

import dev.sagar.todo.model.Todo;
import dev.sagar.todo.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private static final Logger logger = LoggerFactory.getLogger(TodoService.class);

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        logger.debug("Retrieving all todos");

        return todoRepository.findAll();
    }

    public Optional<Todo> getTodoById(Long id) {
        logger.debug("Retrieving todo with id: {}", id);

        return todoRepository.findById(id);
    }

    public Todo createTodo(Todo todo) {
        logger.debug("Creating todo: {}", todo);

        return todoRepository.save(todo);
    }

    public Todo updateTodo(Todo todo) {
        logger.debug("Updating todo: {}", todo);

        return todoRepository.save(todo);
    }

    public void deleteTodo(Todo todo) {
        logger.debug("Deleting todo: {}", todo);

        todoRepository.delete(todo);
    }

    public List<Todo> getTodosByUserId(Long userId) {
        logger.debug("Retrieving todos by user id: {}", userId);

        return todoRepository.findByUserId(userId);
    }
}