package com.github.plan;

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

import java.io.IOException;

/**
 * Copyright (C) Coderion
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GenericTestConfiguration.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
public class UserRepositoryTest extends Assert {

    @Autowired
    UserRepository userRepository;

    @Test
    public void shouldUploadAttachment() throws IOException {
        // When
        User user = new User();
        user.setName("foo");
        user.setLogin("foo");
        user.setPassword("foo");
        User saveUser = userRepository.save(user);

        // Then
        assertEquals(1, userRepository.findAll().size());
    }
}
