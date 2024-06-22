package dev.sagar.todo.service;

import dev.sagar.todo.model.Todo;
import dev.sagar.todo.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTodos() {
        List<Todo> todos = Arrays.asList(
                new Todo(1L, 1L, "Task 1", false),
                new Todo(2L, 2L, "Task 2", true)
        );

        when(todoRepository.findAll()).thenReturn(todos);

        List<Todo> result = todoService.getAllTodos();

        assertEquals(2, result.size());
        verify(todoRepository, times(1)).findAll();
    }

    @Test
    public void testGetTodoById() {
        Todo todo = new Todo(1L, 1L, "Task 1", false);

        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));

        Optional<Todo> result = todoService.getTodoById(1L);

        assertTrue(result.isPresent());
        assertEquals("Task 1", result.get().getTitle());
        verify(todoRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateTodo() {
        Todo todo = new Todo(1L, 1L, "Task 1", false);

        when(todoRepository.save(todo)).thenReturn(todo);

        Todo result = todoService.createTodo(todo);

        assertEquals("Task 1", result.getTitle());
        verify(todoRepository, times(1)).save(todo);
    }

    @Test
    public void testUpdateTodo() {
        Todo todo = new Todo(1L, 1L, "Task 1", false);

        when(todoRepository.save(todo)).thenReturn(todo);

        Todo result = todoService.updateTodo(todo);

        assertEquals("Task 1", result.getTitle());
        verify(todoRepository, times(1)).save(todo);
    }

    @Test
    public void testDeleteTodo() {
        Todo todo = new Todo(1L, 1L, "Task 1", false);

        doNothing().when(todoRepository).delete(todo);

        todoService.deleteTodo(todo);

        verify(todoRepository, times(1)).delete(todo);
    }

    @Test
    public void testGetTodosByUserId() {
        List<Todo> todos = Arrays.asList(
                new Todo(1L, 1L, "Task 1", false),
                new Todo(2L, 1L, "Task 2", true)
        );

        when(todoRepository.findByUserId(1L)).thenReturn(todos);

        List<Todo> result = todoService.getTodosByUserId(1L);

        assertEquals(2, result.size());
        verify(todoRepository, times(1)).findByUserId(1L);
    }
}