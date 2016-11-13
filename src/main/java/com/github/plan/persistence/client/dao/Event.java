package com.github.plan.persistence.client.dao;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class Event {
    @Id
    @GeneratedValue
    private Long id;

    private Calendar timeFrom;
    private Calendar timeTo;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Team team;

    @ManyToOne(fetch = FetchType.EAGER)
    private Room room;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Calendar timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Calendar getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Calendar timeTo) {
        this.timeTo = timeTo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
