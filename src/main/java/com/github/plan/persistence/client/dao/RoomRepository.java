package com.github.plan.persistence.client.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    int countByBuildingAndNumber(String building, String number);

    Room findByBuildingAndNumber(String building, String number);
}
