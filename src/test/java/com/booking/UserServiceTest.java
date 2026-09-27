package com.booking;

import com.booking.dao.UserDAO;
import com.booking.model.User;
import com.booking.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserService userService;
    private UserDAO userDAO;

    @BeforeEach
    void setUp() throws Exception {

        userService = new UserService();

        userDAO = mock(UserDAO.class);

        Field field = UserService.class.getDeclaredField("userDAO");
        field.setAccessible(true);
        field.set(userService, userDAO);
    }

    // ================= CREATE =================

    @Test
    void testAddUser() {

        User user = new User();

        user.setFullName("Test User");
        user.setEmail("test@example.com");
        user.setPasswordHash("password123");
        user.setPhone("9876543210");
        user.setRole("CUSTOMER");
        user.setStatus("ACTIVE");

        userService.addUser(user);

        verify(userDAO, times(1)).addUser(user);
    }

    @Test
    void testAddUserWithNullUser() {

        userService.addUser(null);

        verify(userDAO, never()).addUser(any(User.class));
    }

    // ================= READ BY ID =================

    @Test
    void testGetUserById() {

        User user = new User();

        user.setUserId(1L);
        user.setFullName("Test User");
        user.setEmail("test@example.com");

        when(userDAO.getUserById(1L)).thenReturn(user);

        User result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getUserId());
        assertEquals("Test User", result.getFullName());
        assertEquals("test@example.com", result.getEmail());

        verify(userDAO, times(1)).getUserById(1L);
    }

    @Test
    void testGetUserByIdWithInvalidId() {

        User result = userService.getUserById(0L);

        assertNull(result);

        verify(userDAO, never()).getUserById(anyLong());
    }

    @Test
    void testGetUserByIdWhenUserNotFound() {

        when(userDAO.getUserById(999L)).thenReturn(null);

        User result = userService.getUserById(999L);

        assertNull(result);

        verify(userDAO, times(1)).getUserById(999L);
    }

    // ================= READ ALL =================

    @Test
    void testGetAllUsers() {

        User user1 = new User();
        user1.setUserId(1L);
        user1.setFullName("User One");

        User user2 = new User();
        user2.setUserId(2L);
        user2.setFullName("User Two");

        List<User> users = Arrays.asList(user1, user2);

        when(userDAO.getAllUsers()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("User One", result.get(0).getFullName());
        assertEquals("User Two", result.get(1).getFullName());

        verify(userDAO, times(1)).getAllUsers();
    }

    // ================= UPDATE =================

    @Test
    void testUpdateUser() {

        User user = new User();

        user.setUserId(1L);
        user.setFullName("Updated User");
        user.setEmail("updated@example.com");

        userService.updateUser(user);

        verify(userDAO, times(1)).updateUser(user);
    }

    @Test
    void testUpdateUserWithNullUser() {

        userService.updateUser(null);

        verify(userDAO, never()).updateUser(any(User.class));
    }

    @Test
    void testUpdateUserWithNullId() {

        User user = new User();

        user.setFullName("Test User");

        userService.updateUser(user);

        verify(userDAO, never()).updateUser(any(User.class));
    }

    // ================= DELETE =================

    @Test
    void testDeleteUser() {

        userService.deleteUser(1L);

        verify(userDAO, times(1)).deleteUser(1L);
    }

    @Test
    void testDeleteUserWithInvalidId() {

        userService.deleteUser(0L);

        verify(userDAO, never()).deleteUser(anyLong());
    }

    @Test
    void testDeleteUserWithNullId() {

        userService.deleteUser(null);

        verify(userDAO, never()).deleteUser(anyLong());
    }
}