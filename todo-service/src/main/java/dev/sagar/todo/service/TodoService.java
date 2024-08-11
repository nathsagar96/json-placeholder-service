package dev.sagar.todo.service;

import dev.sagar.todo.model.Todo;
import dev.sagar.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    public Optional<Todo> getTodoById(Integer id) {
        return todoRepository.findById(id);
    }

    public List<Todo> getTodosByUserId(Integer userId) {
        return todoRepository.findByUserId(userId);
    }
}
