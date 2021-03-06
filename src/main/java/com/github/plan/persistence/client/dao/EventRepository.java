package com.github.plan.persistence.client.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Calendar;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("select e from #{#entityName} e where (e.timeFrom>=:timeFrom and e.timeFrom<=:timeTo) or (e.timeTo>=:timeFrom and e.timeTo<=:timeTo)")
    List<Event> findPlannedOrOngoing(@Param("timeFrom") Calendar timeFrom, @Param("timeTo") Calendar timeTo);
}
