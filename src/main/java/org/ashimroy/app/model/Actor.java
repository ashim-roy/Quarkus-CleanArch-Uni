package org.ashimroy.app.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// Entity annotation indicates that this class is a JPA entity.
@Entity
// Table annotation specifies the details of the table that will be used to create the entity in the database.
@Table(name = "actor", schema = "sakila")
public class Actor {
    // These annotations define the primary key for the entity.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "actor_id")
    private short actorId;

    // Basic annotation is the simplest type of mapping to a database column. Basic means that the attribute is mapped to a single column.
    @Basic
    @Column(name = "first_name")
    private String firstName;

    @Basic
    @Column(name = "last_name")
    private String lastName;

    @Basic
    @Column(name = "last_update")
    private Timestamp lastUpdate;

    // ManyToMany annotation indicates a many-to-many relationship between the Actor and Film entities.
    // The mappedBy element indicates that this entity is the inverse of the relationship.
    @ManyToMany(mappedBy = "actors") // here actors field is mapped by the films field in the Film entity.
    private Set<Film> films = new HashSet<>();

    // Standard getters and setters for the fields.

    // equals method to compare two Actor objects for equality.
    @Override
    public boolean equals(Object o) {  // method for comparing two Actor objects for equality.
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return actorId == actor.actorId && Objects.equals(firstName, actor.firstName) && Objects.equals(lastName, actor.lastName) && Objects.equals(lastUpdate, actor.lastUpdate);
    }

    // hashCode method to generate a hash code for the Actor object.
    @Override
    public int hashCode() {
        return Objects.hash(actorId, firstName, lastName, lastUpdate);
    }


    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }
}
