package com.github.plan.base;

import com.github.plan.config.PersistenceJpaConfig;
import com.github.plan.persistence.client.dao.EventRepository;
import com.github.plan.persistence.client.dao.RoomRepository;
import com.github.plan.persistence.client.dao.TeamRepository;
import com.github.plan.persistence.client.dao.UserRepository;
import com.github.plan.service.EventService;
import com.github.plan.service.EventServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.mockito.Mockito.mock;

@Configuration
@Import({PersistenceJpaConfig.class})
public class GenericTestConfiguration {

    @Bean
    UserRepository userRepository() {
        return mock(UserRepository.class);
    }

    @Bean
    EventRepository eventRepository() {
        return mock(EventRepository.class);
    }

    @Bean
    RoomRepository roomRepository() {
        return mock(RoomRepository.class);
    }

    @Bean
    TeamRepository teamRepository() {
        return mock(TeamRepository.class);
    }

    @Bean
    EventService eventService() {
        return new EventServiceImpl();
    }
}
