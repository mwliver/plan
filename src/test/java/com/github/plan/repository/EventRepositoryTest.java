package com.github.plan.repository;

import com.github.plan.base.GenericTestConfiguration;
import com.github.plan.persistence.client.dao.Event;
import com.github.plan.persistence.client.dao.EventRepository;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GenericTestConfiguration.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
@Ignore
public class EventRepositoryTest extends Assert {

    @Autowired
    EventRepository eventRepository;

    @Test
    public void shouldAddEvent() {
        // Given
        Event event = new Event();
        event.setTimeFrom(Calendar.getInstance());
        event.setTimeTo(Calendar.getInstance());
        Event saveEvent = eventRepository.save(event);

        // Then
        assertEquals(1, eventRepository.findAll().size());
    }

    @Test
    public void findPlannedOrOngoing() throws Exception {
        // Given
        Event firstEvent = new Event();
        firstEvent.setTimeFrom(Calendar.getInstance());
        firstEvent.setTimeTo(Calendar.getInstance());

        Event secondEvent = new Event();
        secondEvent.setTimeFrom(Calendar.getInstance());
        secondEvent.setTimeTo(Calendar.getInstance());

        Event thirdEvent = new Event();
        thirdEvent.setTimeFrom(Calendar.getInstance());
        thirdEvent.setTimeTo(Calendar.getInstance());

        eventRepository.save(firstEvent);
        eventRepository.save(secondEvent);
        eventRepository.save(thirdEvent);

        Calendar calendarFrom = Calendar.getInstance();
        calendarFrom.add(Calendar.YEAR, -1);

        Calendar calendarTo = Calendar.getInstance();
        calendarTo.add(Calendar.YEAR, 1);

        // When
        List<Event> plannedOrOngoing = eventRepository.findPlannedOrOngoing(calendarFrom, calendarTo);

        // Then
        assertEquals(3, plannedOrOngoing.size());
    }
}
