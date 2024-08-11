package dev.sagar.user.controller;

import dev.sagar.user.exception.UserNotFoundException;
import dev.sagar.user.model.User;
import dev.sagar.user.service.UserService;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void testGetUsers_whenServiceReturnsUsers_thenReturnUsers() {
        // given
        List<User> users = Instancio.ofList(User.class).size(10).create();
        when(userService.getUsers()).thenReturn(users);

        // when
        List<User> result = userController.getUsers();

        // then
        assertEquals(users, result);
    }

    @Test
    void testGetUserById_whenServiceReturnsUser_thenReturnUser() {
        // given
        User user = Instancio.create(User.class);
        when(userService.getUserById(eq(1))).thenReturn(Optional.of(user));

        // when
        User result = userController.getUserById(1);

        // then
        assertEquals(user, result);
    }

    @Test
    public void testGetUserById_throwsUserNotFoundException_whenUserNotFound() {
        // given
        when(userService.getUserById(eq(1))).thenReturn(Optional.empty());

        // when
        assertThrows(UserNotFoundException.class, () -> userController.getUserById(1));
    }

    @Test
    void testCreateUser_returnsCreatedUser() {
        // given
        User user = Instancio.of(User.class).ignore(field(User::getId)).create();
        User createdUser = Instancio.of(User.class).set(field(User::getId), 11).create();

        // when
        User result = userController.createUser(user);

        // then
        assertEquals(result.getId(), createdUser.getId());
    }

    @Test
    void testUpdateUser_returnsUpdatedUser() {
        // given
        User user = Instancio.of(User.class).set(field(User::getId), 1).create();
        User updatedUser = Instancio.of(User.class).set(field(User::getId), 1).create();
        when(userService.getUserById(eq(1))).thenReturn(Optional.of(user));

        // when
        User result = userController.updateUser(1, updatedUser);

        // then
        assertEquals(result.getId(), user.getId());
        assertEquals(result.getName(), updatedUser.getName());
        assertEquals(result.getEmail(), updatedUser.getEmail());
        assertEquals(result.getPhone(), updatedUser.getPhone());
        assertEquals(result.getAddress(), updatedUser.getAddress());
    }

    @Test
    public void testUpdateUser_throwsUserNotFoundException_whenUserNotFound() {
        // given
        User updatedUser = Instancio.create(User.class);
        when(userService.getUserById(eq(1))).thenReturn(Optional.empty());

        // when
        assertThrows(UserNotFoundException.class, () -> userController.updateUser(1, updatedUser));
    }
}
