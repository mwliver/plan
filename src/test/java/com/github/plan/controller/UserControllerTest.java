package com.github.plan.controller;

import com.github.plan.base.GenericTestConfiguration;
import com.github.plan.persistence.client.dao.User;
import com.github.plan.persistence.client.dao.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GenericTestConfiguration.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
public class UserControllerTest extends Assert {

    @Autowired
    private UserController userController;

    @Autowired
    private UserRepository userRepository;

    private UserController userControllerMock;

    @Before
    public void setUp() {
        userControllerMock = mock(userController.getClass());
    }

    @Test
    public void userTestMock() throws Exception {
        // Given
        User user = new User();
        user.setLogin("mw");
        user.setPassword("mw");
        user.setName("mw");

        // When
        when(userControllerMock.saveUser(user)).thenReturn(new ResponseEntity<String>(HttpStatus.ACCEPTED));
        ResponseEntity<String> response = userControllerMock.saveUser(user);

        // Then
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    public void userTestImpl() throws Exception {
        // Given
        User user = new User();
        user.setLogin("mw");
        user.setPassword("mw");
        user.setName("mw");

        // When
        ResponseEntity<String> response = userController.saveUser(user);

        // Then
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    public void removeTestMock() throws Exception {
        // When
        when(userControllerMock.removeUser(any())).thenReturn(new ResponseEntity<String>(HttpStatus.OK));
        ResponseEntity<String> response = userControllerMock.removeUser(1L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void removeTestImpl() throws Exception {
        // Given
        User user = new User();
        user.setLogin("mw");
        user.setPassword("mw");
        user.setName("mw");
        user = userRepository.save(user);

        // When
        ResponseEntity<String> response = userController.removeUser(user.getId());

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void listTestMock() throws Exception {
        // Given
        User user = new User();
        user.setLogin("mw");
        user.setPassword("mw");
        user.setName("mw");

        User user2 = new User();
        user2.setLogin("pass");
        user2.setPassword("pass");
        user2.setName("pass");

        List<User> expected = Arrays.asList(user, user2);

        // When
        when(userControllerMock.getUsers()).thenReturn(expected);
        List<User> actual = userControllerMock.getUsers();

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void listTestImpl() throws Exception {
        // Given
        User user = new User();
        user.setLogin("mw");
        user.setPassword("mw");
        user.setName("mw");
        user = userRepository.save(user);

        User user2 = new User();
        user2.setLogin("pass");
        user2.setPassword("pass");
        user2.setName("pass");
        user2 = userRepository.save(user2);

        List<User> expected = Arrays.asList(user, user2);

        // When
        List<User> actual = userController.getUsers();

        // Then
        assertEquals(expected, actual);
    }
}
