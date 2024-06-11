package org.ashimroy.app.data.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "actor", schema = "sakila")
public class Actor extends PanacheEntityBase {
    @Column(name = "first_name")
    public String firstName;

    @Column(name = "last_name")
    public String lastName;

    @Column(name = "last_update")
    public Timestamp lastUpdate;

    @ManyToMany(mappedBy = "actors")
    public Set<Film> films;

    
}

/*
 * your Actor class is correctly refactored to use Quarkus Panache. Here are a few things to note:
PanacheEntity automatically provides an id field, so you no longer need to define actorId.
Fields are public in Panache entities. This is a conscious design decision to simplify the code.
You no longer need getters and setters. You can access the fields directly.
You've correctly used @ManyToMany annotation for the films field.
You've removed the equals and hashCode methods. If you need to compare Actor entities or use them in a HashSet or HashMap, you might want to add these methods back. Panache doesn't provide a default implementation for these methods.
 */