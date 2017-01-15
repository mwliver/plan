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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GenericTestConfiguration.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
public class LoginControllerTest extends Assert {

    @Autowired
    private LoginController loginController;

    @Autowired
    private UserRepository userRepository;

    private LoginController loginControllerMock;

    @Before
    public void setUp() {
        loginControllerMock = mock(loginController.getClass());
    }

    @Test
    public void loginTestMock() throws Exception {
        // Given
        String username = "mw";
        String pass = "pass";

        // When
        when(loginControllerMock.authenticate(username, pass)).thenReturn(new ResponseEntity<String>(HttpStatus.ACCEPTED));
        ResponseEntity<String> response = loginControllerMock.authenticate(username, pass);

        // Then
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    public void loginTestImpl() throws Exception {
        // Given
        String username = "mw";
        String pass = "pass";

        User user = new User();
        user.setLogin(username);
        user.setPassword(pass);
        userRepository.save(user);

        // When
        ResponseEntity<String> response = loginController.authenticate(username, pass);

        // Then
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }
}
