package com.github.plan.persistance.client.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Copyright (C) Coderion sp. z o.o
 */
public interface UserDao extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {
}
