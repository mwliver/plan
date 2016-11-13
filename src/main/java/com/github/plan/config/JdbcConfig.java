package com.github.plan.config;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class JdbcConfig {

    @Autowired
    Properties additionalProperties;

    @Autowired
    DataSource dataSource;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.github.plan.persistence.client.dao");
        factory.setJpaProperties(additionalProperties);
        factory.setDataSource(dataSource);
        factory.afterPropertiesSet();

        return factory;
    }

    @Bean
    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty(Environment.HBM2DDL_AUTO, "none");
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty(Environment.FORMAT_SQL, "true");
        properties.setProperty(Environment.SHOW_SQL, "true");
        properties.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "org.springframework.orm.hibernate4.SpringSessionContext");
        return properties;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost/zpp");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        return dataSource;
    }
}
