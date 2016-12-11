package com.github.plan.persistence.client.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
    int countByName(String name);
}
