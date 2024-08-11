package dev.sagar.user.service;

import dev.sagar.user.model.User;
import dev.sagar.user.repository.UserRepository;
import org.instancio.Instancio;
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
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetUsers_whenRepositoryReturnsUsers_thenReturnUsers() {
        // given
        List<User> expectedUsers = Instancio.ofList(User.class).size(10).create();
        when(userRepository.findAll()).thenReturn(expectedUsers);

        // when
        List<User> actualUsers = userService.getUsers();

        // then
        assertEquals(expectedUsers, actualUsers);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetUserById_whenRepositoryReturnsUser_thenReturnUser() {
        // given
        User expectedUser = Instancio.create(User.class);
        when(userRepository.findById(eq(1))).thenReturn(Optional.of(expectedUser));

        // when
        Optional<User> actualUser = userService.getUserById(1);

        // then
        assertTrue(actualUser.isPresent());
        assertEquals(expectedUser, actualUser.get());
        verify(userRepository, times(1)).findById(eq(1));
    }

    @Test
    public void testGetUserById_whenRepositoryReturnsEmpty_thenReturnEmptyOptional() {
        // given
        when(userRepository.findById(eq(1))).thenReturn(Optional.empty());

        // when
        Optional<User> actualUser = userService.getUserById(1);

        // then
        assertFalse(actualUser.isPresent());
        verify(userRepository, times(1)).findById(eq(1));
    }
}
