package dev.sagar.todo.controller;

import dev.sagar.todo.exception.TodoNotFoundException;
import dev.sagar.todo.model.Todo;
import dev.sagar.todo.service.TodoService;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/todos")
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public List<Todo> getTodos() {
        return todoService.getTodos();
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable int id) {
        return todoService
                .getTodoById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + id));
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        todo.setId(201);
        return todo;
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable int id, @RequestBody Todo todoDetails) {
        Todo todo =
                todoService
                        .getTodoById(id)
                        .orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + id));

        todo.setId(id);
        todo.setTitle(todoDetails.getTitle());
        todo.setCompleted(todoDetails.getCompleted());
        todo.setUserId(todoDetails.getUserId());
        return todo;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable int id) {
    }

    @GetMapping("/user/{userId}")
    public List<Todo> getTodosByUserId(@PathVariable int userId) {
        return todoService.getTodosByUserId(userId);
    }
}
