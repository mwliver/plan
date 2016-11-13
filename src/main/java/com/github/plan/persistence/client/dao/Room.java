package com.github.plan.persistence.client.dao;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Room {
    @Id
    @GeneratedValue
    private Long id;

    private String building;
    private String number;

    @OneToMany(fetch = FetchType.EAGER)
    @OrderColumn
    private List<Event> events = new ArrayList<Event>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    @Override
    public String toString() {
        return building + number;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}
