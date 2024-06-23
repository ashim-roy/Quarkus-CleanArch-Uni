package org.ashimroy.app.domain.entity;

import java.sql.Timestamp;
import java.util.Set;

public class Actor { 
// represents the Actor entity in the domain model. doesnt have any JPA annotations nor depend on any persistence libraries.
    private Long id;
    private String firstName;
    private String lastName;
    private Timestamp lastUpdate;
    private Set<Film> films;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }
}
/*
 * your Actor class is correctly refactored to use Quarkus Panache. Here are a few things to note:
PanacheEntity automatically provides an id field, so you no longer need to define actorId.
Fields are public in Panache entities. This is a conscious design decision to simplify the code.
You no longer need getters and setters. You can access the fields directly.
You've correctly used @ManyToMany annotation for the films field.
You've removed the equals and hashCode methods. If you need to compare Actor entities or use them in a HashSet or HashMap, you might want to add these methods back. Panache doesn't provide a default implementation for these methods.
 */