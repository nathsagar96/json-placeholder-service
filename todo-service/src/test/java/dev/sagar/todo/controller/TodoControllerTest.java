package dev.sagar.todo.controller;

import dev.sagar.todo.exception.TodoNotFoundException;
import dev.sagar.todo.model.Todo;
import dev.sagar.todo.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoControllerTest {

    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoController todoController;

    @Test
    void testGetTodos_whenServiceReturnsTodos_thenReturnTodos() {
        // given
        List<Todo> todos = List
                .of(new Todo(1, "title 1", false, 1),
                        new Todo(2, "title 2", true, 1));

        when(todoService.getTodos()).thenReturn(todos);

        // when
        List<Todo> result = todoController.getTodos();

        // then
        assertEquals(todos, result);
    }

    @Test
    void testGetTodoById_whenServiceReturnsTodo_thenReturnTodo() {
        // given
        Todo todo = new Todo(1, "title 1", false, 1);
        when(todoService.getTodoById(eq(1))).thenReturn(Optional.of(todo));

        // when
        Todo result = todoController.getTodoById(1);

        // then
        assertEquals(todo, result);
    }

    @Test
    public void testGetTodoById_throwsTodoNotFoundException_whenTodoNotFound() {
        // given
        when(todoService.getTodoById(eq(1))).thenReturn(Optional.empty());

        // when
        assertThrows(TodoNotFoundException.class, () -> todoController.getTodoById(1));
    }

    @Test
    void testCreateTodo_returnsCreatedTodo() {
        // given
        Todo todo = new Todo(201, "title 1", false, 1);
        Todo createdTodo = new Todo(201, "title 1", false, 1);

        // when
        Todo result = todoController.createTodo(todo);

        // then
        assertEquals(result.getId(), createdTodo.getId());
    }

    @Test
    void testUpdateTodo_returnsUpdatedTodo() {
        // given
        Todo todo = new Todo(1, "title 1", false, 1);
        Todo updatedTodo = new Todo(1, "title 1", true, 1);
        when(todoService.getTodoById(eq(1))).thenReturn(Optional.of(todo));

        // when
        Todo result = todoController.updateTodo(1, updatedTodo);

        // then
        assertEquals(result.getCompleted(), updatedTodo.getCompleted());
        assertEquals(result.getTitle(), updatedTodo.getTitle());
        assertEquals(result.getUserId(), updatedTodo.getUserId());
    }

    @Test
    public void testUpdateTodo_throwsTodoNotFoundException_whenTodoNotFound() {
        // given
        Todo updatedTodo = new Todo(1, "title 1", false, 1);
        when(todoService.getTodoById(eq(1))).thenReturn(Optional.empty());

        // when
        assertThrows(TodoNotFoundException.class, () -> todoController.updateTodo(1, updatedTodo));
    }

    @Test
    void testGetTodosByUserId_whenServiceReturnsTodos_thenReturnTodos() {
        // given
        List<Todo> todos = List
                .of(new Todo(1, "title 1", false, 1),
                        new Todo(2, "title 2", true, 1));
        when(todoService.getTodosByUserId(eq(1))).thenReturn(todos);

        // when
        List<Todo> result = todoController.getTodosByUserId(1);

        // then
        assertEquals(todos, result);
    }
}
