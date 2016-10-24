package com.github.plan.persistence.client.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Calendar;

@Entity
public class Event {
    @Id
    private Long id;

    private Calendar timeFrom;
    private Calendar timeTo;

    @ManyToOne
    private User user;

    @ManyToOne
    private Group group;

    @ManyToOne
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


}
