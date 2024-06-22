package dev.sagar.user.service;

import dev.sagar.user.model.User;
import dev.sagar.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setUsername("johndoe");
        user.setEmail("johndoe@example.com");

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = Collections.singletonList(user);
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserById(1L);

        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetUserById_NotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<User> result = userService.getUserById(1L);

        assertFalse(result.isPresent());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.createUser(user);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testUpdateUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.updateUser(user);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(userRepository).delete(any(User.class));

        userService.deleteUser(user);

        verify(userRepository, times(1)).delete(user);
    }
}
