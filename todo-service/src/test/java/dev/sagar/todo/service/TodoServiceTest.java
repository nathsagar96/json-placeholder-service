package dev.sagar.todo.service;

import dev.sagar.todo.model.Todo;
import dev.sagar.todo.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @Test
    public void testGetTodos_whenRepositoryReturnsTodos_thenReturnTodos() {
        // given
        List<Todo> expectedTodos = List
                .of(new Todo(1, "title 1", false, 1),
                        new Todo(2, "title 2", true, 1));
        when(todoRepository.findAll()).thenReturn(expectedTodos);

        // when
        List<Todo> actualTodos = todoService.getTodos();

        // then
        assertEquals(expectedTodos, actualTodos);
        verify(todoRepository, times(1)).findAll();
    }

    @Test
    public void testGetTodoById_whenRepositoryReturnsTodo_thenReturnTodo() {
        // given
        Todo expectedTodo = new Todo(1, "title 1", false, 1);
        when(todoRepository.findById(eq(1))).thenReturn(Optional.of(expectedTodo));

        // when
        Optional<Todo> actualTodo = todoService.getTodoById(1);

        // then
        assertTrue(actualTodo.isPresent());
        assertEquals(expectedTodo, actualTodo.get());
        verify(todoRepository, times(1)).findById(eq(1));
    }

    @Test
    public void testGetTodoById_whenRepositoryReturnsEmpty_thenReturnEmptyOptional() {
        // given
        when(todoRepository.findById(eq(1))).thenReturn(Optional.empty());

        // when
        Optional<Todo> actualTodo = todoService.getTodoById(1);

        // then
        assertFalse(actualTodo.isPresent());
        verify(todoRepository, times(1)).findById(eq(1));
    }

    @Test
    public void testGetTodosByUserId_whenRepositoryReturnsTodos_thenReturnTodos() {
        // given
        List<Todo> expectedTodos = List
                .of(new Todo(1, "title 1", false, 1),
                        new Todo(2, "title 2", true, 1));
        when(todoRepository.findByUserId(eq(1))).thenReturn(expectedTodos);

        // when
        List<Todo> actualTodos = todoService.getTodosByUserId(1);

        // then
        assertEquals(expectedTodos, actualTodos);
        verify(todoRepository, times(1)).findByUserId(eq(1));
    }
}
