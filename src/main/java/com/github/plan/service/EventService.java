package com.github.plan.service;

import com.github.plan.persistence.client.dao.Event;
import com.github.plan.persistence.client.dao.Room;
import com.github.plan.persistence.client.dao.Team;
import com.github.plan.persistence.client.dao.User;

public interface EventService {

    Boolean addEvent(Event event, Team team, Room room, User user);
}
