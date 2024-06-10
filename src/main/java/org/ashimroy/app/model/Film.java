package org.ashimroy.app.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Optional;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.Id;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "film", schema = "sakila")
public class Film extends PanacheEntityBase {
    @Column(name = "title", nullable = false)
    public String title;

    @Column(name = "description")
    public String description;

    @Column(name = "language_id", nullable = false)
    public short languageId;

    @Column(name = "original_language_id")
    public Short originalLanguageId;

    @Column(name = "rental_duration", nullable = false)
    public short rentalDuration;

    @Column(name = "rental_rate", columnDefinition = "decimal(4,2)", nullable = false)
    public Float rentalRate;

    @Column(name = "length")
    public Short length;

    @Column(name = "replacement_cost", nullable = false)
    public BigDecimal replacementCost;

    @Column(name = "rating", columnDefinition = "enum('G', 'PG', 'PG-13', 'R', 'NC-17')")
    public String rating;

    @Column(name = "special_features", columnDefinition = "set('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')")
    public String specialFeatures;

    @Column(name = "last_update", nullable = false)
    public Timestamp lastUpdate;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "film_actor",
            joinColumns = {@JoinColumn(name = "film_id")},
            inverseJoinColumns = {@JoinColumn(name = "actor_id")}
    )
    public List<Actor> actors;

    // Proper getter methods
    public String getTitle() {
        return this.title;
    }
    
    public Short getLength() {
        return this.length;
    }
    @PersistenceContext
    EntityManager em;

    public Optional<Film> findByIdOptional(Long id) {
        return Optional.ofNullable(em.find(Film.class, id));
    }
}

/*
 * Yes, your Film class is correctly refactored to use Quarkus Panache. Here are a few things to note:

PanacheEntity automatically provides an id field, so you no longer need to define filmId.

Fields are public in Panache entities. This is a conscious design decision to simplify the code.

You no longer need getters and setters. You can access the fields directly.

You've correctly used @ManyToMany annotation for the actors field. The cascade attribute is set to PERSIST and MERGE, which means that persisting or merging a Film entity will also persist or merge its associated Actor entities.

You've removed the equals and hashCode methods. If you need to compare Film entities or use them in a HashSet or HashMap, you might want to add these methods back. Panache doesn't provide a default implementation for these methods.
 */

 