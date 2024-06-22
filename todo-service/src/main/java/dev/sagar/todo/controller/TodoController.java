package dev.sagar.todo.controller;

import dev.sagar.todo.exception.TodoNotFoundException;
import dev.sagar.todo.model.Todo;
import dev.sagar.todo.service.TodoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/todos")
@Validated
public class TodoController {

    private static final Logger logger = LoggerFactory.getLogger(TodoController.class);

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        logger.info("Fetching all todos");

        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable Long id) {
        logger.info("Fetching todo with id: {}", id);

        return todoService.getTodoById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + id));
    }

    @PostMapping
    public Todo createTodo(@Valid @RequestBody Todo todo) {
        logger.info("Creating todo: {}", todo);

        return todoService.createTodo(todo);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @Valid @RequestBody Todo todoDetails) {
        logger.info("Updating todo with id: {}", id);

        Todo todo = todoService.getTodoById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + id));

        todo.setUserId(todoDetails.getUserId());
        todo.setTitle(todoDetails.getTitle());
        todo.setCompleted(todoDetails.getCompleted());

        return todoService.updateTodo(todo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        logger.info("Deleting todo with id: {}", id);

        Todo todo = todoService.getTodoById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + id));

        todoService.deleteTodo(todo);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public List<Todo> getTodosByUserId(@PathVariable Long userId) {
        logger.info("Fetching todos by user id: {}", userId);

        return todoService.getTodosByUserId(userId);
    }
}