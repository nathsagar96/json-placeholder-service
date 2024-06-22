package dev.sagar.user.controller;

import dev.sagar.user.exception.GlobalExceptionHandler;
import dev.sagar.user.model.User;
import dev.sagar.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
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

public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setUsername("johndoe");
        user.setEmail("johndoe@example.com");

        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    public void testGetAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(Collections.singletonList(user));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].username").value("johndoe"))
                .andExpect(jsonPath("$[0].email").value("johndoe@example.com"));

        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void testGetUserById() throws Exception {
        when(userService.getUserById(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.username").value("johndoe"))
                .andExpect(jsonPath("$.email").value("johndoe@example.com"));

        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    public void testGetUserById_NotFound() throws Exception {
        when(userService.getUserById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isNotFound());

        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    public void testCreateUser() throws Exception {
        when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"username\":\"johndoe\",\"email\":\"johndoe@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.username").value("johndoe"))
                .andExpect(jsonPath("$.email").value("johndoe@example.com"));

        verify(userService, times(1)).createUser(any(User.class));
    }

    @Test
    public void testUpdateUser() throws Exception {
        when(userService.getUserById(1L)).thenReturn(Optional.of(user));
        when(userService.updateUser(any(User.class))).thenReturn(user);

        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"username\":\"johndoe\",\"email\":\"johndoe@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.username").value("johndoe"))
                .andExpect(jsonPath("$.email").value("johndoe@example.com"));

        verify(userService, times(1)).getUserById(1L);
        verify(userService, times(1)).updateUser(any(User.class));
    }

    @Test
    public void testUpdateUser_NotFound() throws Exception {
        when(userService.getUserById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"username\":\"johndoe\",\"email\":\"johndoe@example.com\"}"))
                .andExpect(status().isNotFound());

        verify(userService, times(1)).getUserById(1L);
        verify(userService, never()).updateUser(any(User.class));
    }

    @Test
    public void testDeleteUser() throws Exception {
        when(userService.getUserById(1L)).thenReturn(Optional.of(user));
        doNothing().when(userService).deleteUser(any(User.class));

        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).getUserById(1L);
        verify(userService, times(1)).deleteUser(any(User.class));
    }

    @Test
    public void testDeleteUser_NotFound() throws Exception {
        when(userService.getUserById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isNotFound());

        verify(userService, times(1)).getUserById(1L);
        verify(userService, never()).deleteUser(any(User.class));
    }
}
