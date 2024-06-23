package org.ashimroy.app.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import java.sql.Timestamp;
import java.util.List;
import javax.persistence.*;

//These entities are used for persistence and represent how actors and films are stored in the database. 
//They are typically annotated with JPA annotations to define their mapping to database tables.
@Entity
@Table(name = "actor")
public class ActorEntity extends PanacheEntityBase {  // inside the infrastructure layer and uses PanacheEntityBase as the base class

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String firstName;
    public String lastName;
    public Timestamp lastUpdate;

    @ManyToMany(mappedBy = "actors")
    public List<FilmEntity> films;

    // Getters and Setters
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

    public List<FilmEntity> getFilms() {
        return films;
    }

    public void setFilms(List<FilmEntity> films) {
        this.films = films;
    }
}
