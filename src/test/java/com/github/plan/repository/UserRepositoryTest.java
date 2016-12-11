package com.github.plan.repository;

import com.github.plan.base.GenericTestConfiguration;
import com.github.plan.persistence.client.dao.User;
import com.github.plan.persistence.client.dao.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GenericTestConfiguration.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
public class UserRepositoryTest extends Assert {

    @Autowired
    UserRepository userRepository;

    @Test
    public void shouldAddUser() {
        // Given
        User user = new User();
        user.setName("foo");
        user.setLogin("foo");
        user.setPassword("foo");
        User saveUser = userRepository.save(user);

        // Then
        assertEquals(1, userRepository.findAll().size());
    }

    @Test
    public void countByLoginTest() throws Exception {
        // Given
        User user = new User();
        user.setLogin("mw");
        user.setName("michal");
        user.setPassword("pass");
        userRepository.save(user);

        // When
        int countByName = userRepository.countByLogin("mw");

        // Then
        assertEquals(1, countByName);
    }

    @Test
    public void countByLoginTest2() throws Exception {
        // Given
        User user = new User();
        user.setLogin("mw");
        user.setName("michal");
        user.setPassword("pass");
        userRepository.save(user);

        // When
        int countByName = userRepository.countByLogin("mww");

        // Then
        assertEquals(0, countByName);
    }

    @Test
    public void countByLoginTest3() throws Exception {
        // Given
        User user = new User();
        user.setLogin("mw");
        user.setName("michal");
        user.setPassword("pass");
        userRepository.save(user);

        User userSecond = new User();
        userSecond.setLogin("mww");
        userSecond.setName("jan");
        userSecond.setPassword("pass2");
        userRepository.save(userSecond);

        // When
        int countByName = userRepository.countByLogin("mw");

        // Then
        assertEquals(1, countByName);
    }

    @Test
    public void findByLoginTest() throws Exception {
        // Given
        User user = new User();
        user.setLogin("mw");
        user.setName("michal");
        user.setPassword("pass");
        userRepository.save(user);

        // When
        User userFound = userRepository.findByLogin("mw");

        // Then
        assertEquals("michal", userFound.getName());
    }

    @Test
    public void findByLoginTest2() throws Exception {
        // Given
        User user = new User();
        user.setLogin("mw");
        user.setName("michal");
        user.setPassword("pass");
        userRepository.save(user);

        // When
        User userFound = userRepository.findByLogin("mww");

        // Then
        assertNull(userFound);
    }

    @Test
    public void findByLoginTest3() throws Exception {
        // Given
        User user = new User();
        user.setLogin("mw");
        user.setName("michal");
        user.setPassword("pass");
        userRepository.save(user);

        User userSecond = new User();
        userSecond.setLogin("mww");
        userSecond.setName("jan");
        userSecond.setPassword("pass2");
        userRepository.save(userSecond);

        // When
        User userFound = userRepository.findByLogin("mw");

        // Then
        assertEquals("michal", userFound.getName());
    }
}
