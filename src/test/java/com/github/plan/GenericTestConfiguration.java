package com.github.plan;

import com.github.plan.config.PersistenceJpaConfig;
import com.github.plan.persistence.client.dao.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.mockito.Mockito.mock;

/**
 * Copyright (C) Coderion
 */
@Configuration
@Import({PersistenceJpaConfig.class})
public class GenericTestConfiguration {

    @Bean
    UserRepository userRepository() {
        return mock(UserRepository.class);
    }
}
