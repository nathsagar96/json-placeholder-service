package dev.sagar.todo.controller;

import dev.sagar.todo.exception.GlobalExceptionHandler;
import dev.sagar.todo.model.Todo;
import dev.sagar.todo.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoController todoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(todoController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    public void testGetAllTodos() throws Exception {
        List<Todo> todos = Arrays.asList(
                new Todo(1L, 1L, "Task 1", false),
                new Todo(2L, 2L, "Task 2", true)
        );

        when(todoService.getAllTodos()).thenReturn(todos);

        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(todos.size()));

        verify(todoService, times(1)).getAllTodos();
    }

    @Test
    public void testGetTodoById() throws Exception {
        Todo todo = new Todo(1L, 1L, "Task 1", false);

        when(todoService.getTodoById(1L)).thenReturn(Optional.of(todo));

        mockMvc.perform(get("/todos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Task 1"));

        verify(todoService, times(1)).getTodoById(1L);
    }

    @Test
    public void testGetTodoById_NotFound() throws Exception {
        when(todoService.getTodoById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/todos/1"))
                .andExpect(status().isNotFound());

        verify(todoService, times(1)).getTodoById(1L);
    }

    @Test
    public void testCreateTodo() throws Exception {
        Todo todo = new Todo(1L, 1L, "Task 1", false);

        when(todoService.createTodo(any(Todo.class))).thenReturn(todo);

        mockMvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\": 1,\"title\": \"Task 1\",\"completed\": false}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Task 1"));

        verify(todoService, times(1)).createTodo(any(Todo.class));
    }

    @Test
    public void testUpdateTodo() throws Exception {
        Todo todo = new Todo(1L, 1L, "Task 1", false);
        Todo updatedTodo = new Todo(1L, 1L, "Updated Task", true);

        when(todoService.getTodoById(1L)).thenReturn(Optional.of(todo));
        when(todoService.updateTodo(any(Todo.class))).thenReturn(updatedTodo);

        mockMvc.perform(put("/todos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\": 1,\"title\": \"Updated Task\",\"completed\": false}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Task"));

        verify(todoService, times(1)).getTodoById(1L);
        verify(todoService, times(1)).updateTodo(any(Todo.class));
    }

    @Test
    public void testUpdateTodo_NotFound() throws Exception {
        Todo updatedTodo = new Todo(1L, 1L, "Updated Task", true);

        when(todoService.getTodoById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/todos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\": 1,\"title\": \"Updated Task\",\"completed\": false}"))
                .andExpect(status().isNotFound());

        verify(todoService, times(1)).getTodoById(1L);
        verify(todoService, never()).updateTodo(any(Todo.class));
    }

    @Test
    public void testDeleteTodo() throws Exception {
        Todo todo = new Todo(1L, 1L, "Task 1", false);

        when(todoService.getTodoById(1L)).thenReturn(Optional.of(todo));
        doNothing().when(todoService).deleteTodo(any(Todo.class));

        mockMvc.perform(delete("/todos/1"))
                .andExpect(status().isNoContent());

        verify(todoService, times(1)).getTodoById(1L);
        verify(todoService, times(1)).deleteTodo(any(Todo.class));
    }

    @Test
    public void testDeleteTodo_NotFound() throws Exception {
        when(todoService.getTodoById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/todos/1"))
                .andExpect(status().isNotFound());

        verify(todoService, times(1)).getTodoById(1L);
        verify(todoService, never()).deleteTodo(any(Todo.class));
    }

    @Test
    public void testGetTodosByUserId() throws Exception {
        List<Todo> todos = Arrays.asList(
                new Todo(1L, 1L, "Task 1", false),
                new Todo(2L, 1L, "Task 2", true)
        );

        when(todoService.getTodosByUserId(1L)).thenReturn(todos);

        mockMvc.perform(get("/todos/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(todos.size()));

        verify(todoService, times(1)).getTodosByUserId(1L);
    }
}