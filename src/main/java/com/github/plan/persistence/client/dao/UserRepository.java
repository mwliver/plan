package com.github.plan.persistence.client.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    int countByLogin(String login);

    User findByLogin(String login);
}
