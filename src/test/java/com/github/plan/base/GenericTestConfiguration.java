package com.github.plan.base;

import com.github.plan.config.PersistenceJpaConfig;
import com.github.plan.controller.RoomController;
import com.github.plan.controller.UserController;
import com.github.plan.persistence.client.dao.EventRepository;
import com.github.plan.persistence.client.dao.RoomRepository;
import com.github.plan.persistence.client.dao.TeamRepository;
import com.github.plan.persistence.client.dao.UserRepository;
import com.github.plan.service.EventService;
import com.github.plan.service.EventServiceImpl;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.Properties;

import static org.mockito.Mockito.mock;

@Configuration
@Import({PersistenceJpaConfig.class})
public class GenericTestConfiguration {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.github.plan.persistence.client.dao");
        factory.setJpaProperties(additionalProperties());
        factory.afterPropertiesSet();

        return factory;
    }

    @Bean
    public Properties additionalProperties() {
        Properties properties = new Properties();

        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty(Environment.DRIVER, "org.h2.Driver");
        properties.setProperty(Environment.URL, "jdbc:h2:mem:test;MODE=PostgreSQL");
        properties.setProperty(Environment.USER, "sa");
        properties.setProperty(Environment.PASS, "");
        properties.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "org.springframework.orm.hibernate4.SpringSessionContext");
        properties.setProperty(Environment.SHOW_SQL, "true");
        properties.setProperty(Environment.HBM2DDL_AUTO, "create");
        properties.setProperty(Environment.USE_SECOND_LEVEL_CACHE, "false");

        return properties;
    }

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

    @Bean
    UserController userController() {
        return new UserController();
    }

    @Bean
    RoomController roomController() {
        return new RoomController();
    }
}
