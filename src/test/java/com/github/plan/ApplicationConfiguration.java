package com.github.plan;

import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class ApplicationConfiguration {

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

        return properties;
    }
}
