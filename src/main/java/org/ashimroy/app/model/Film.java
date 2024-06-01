package org.ashimroy.app.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// The @Entity annotation specifies that the class is an entity and is mapped to a database table.
@Entity
// The @Table annotation specifies the name of the database table to be used for mapping.
@Table(name = "film", schema = "sakila")
public class Film {
    
    // Default constructor
    public Film() {}
    
    // Constructor with parameters
    public Film(short filmId, String title, short length) {
        this.filmId = filmId; 
        this.title = title; 
        this.length = length;
    }
    
    // The @Id annotation specifies the primary key of an entity.
    // The @GeneratedValue annotation provides for the specification of generation strategies for the values of primary keys.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "film_id")
    private short filmId;
    
    // The @Basic annotation is a marker annotation, it can be applied to a persistent property or instance variable of any of the following types: Java primitive types, wrappers of the primitive types, String, java.math.BigInteger, java.math.BigDecimal, java.util.Date, java.util.Calendar, java.sql.Date, java.sql.Time, java.sql.Timestamp, byte[], Byte[], char[], Character[], enums, and any other type that implements Serializable.
    @Basic
    @Column(name = "title")
    private String title;

    // The @Basic annotation is used to specify the mapping of a basic attribute.
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "language_id")
    private short languageId;
    @Basic
    @Column(name = "original_language_id")
    private Short originalLanguageId;
    @Basic
    @Column(name = "rental_duration")
    private short rentalDuration;
    @Basic
    @Column(name = "rental_rate", columnDefinition = "decimal(4,2)")
    private Float rentalRate;
    @Basic
    @Column(name = "length")
    private Short length;
    @Basic
    @Column(name = "replacement_cost")
    private BigDecimal replacementCost;
    @Basic
    @Column(name = "rating", columnDefinition = "enum('G', 'PG', 'PG-13', 'R', 'NC-17')") // rating has enum values in the database
    private String rating;
    @Basic
    @Column(name = "special_features", columnDefinition = "set('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')")
    private String specialFeatures;
    @Basic
    @Column(name = "last_update")
    private Timestamp lastUpdate;

    //film_actor is join table..
    // The @ManyToMany annotation is used in both sides of many-to-many relationship.
    // In this case, it's used in the relationship between Film and Actor entities.
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "film_actor",
            joinColumns = { @JoinColumn(name = "film_id") },
            inverseJoinColumns = { @JoinColumn(name = "actor_id") }
    )
    private List<Actor> actors = new ArrayList<>();


    public short getFilmId() {
        return filmId;
    }

    public void setFilmId(short filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public short getLanguageId() {
        return languageId;
    }

    public void setLanguageId(short languageId) {
        this.languageId = languageId;
    }

    public Short getOriginalLanguageId() {
        return originalLanguageId;
    }

    public void setOriginalLanguageId(Short originalLanguageId) {
        this.originalLanguageId = originalLanguageId;
    }

    public short getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(short rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public Float getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(Float rentalRate) {
        this.rentalRate = rentalRate;
    }

    public Short getLength() {
        return length;
    }

    public void setLength(Short length) {
        this.length = length;
    }

    public BigDecimal getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(BigDecimal replacementCost) {
        this.replacementCost = replacementCost;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    // Overriding equals method for Film class
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // if both are same object
        if (o == null || getClass() != o.getClass()) return false; // if object is null or different class
        Film film = (Film) o; // typecast object to Film
        // compare all fields
        return filmId == film.filmId && languageId == film.languageId && rentalDuration == film.rentalDuration && Objects.equals(title, film.title) && Objects.equals(description, film.description) && Objects.equals(originalLanguageId, film.originalLanguageId) && Objects.equals(rentalRate, film.rentalRate) && Objects.equals(length, film.length) && Objects.equals(replacementCost, film.replacementCost) && Objects.equals(rating, film.rating) && Objects.equals(specialFeatures, film.specialFeatures) && Objects.equals(lastUpdate, film.lastUpdate);
    }

    // Overriding hashCode method for Film class
    @Override
    public int hashCode() {
        // generate hashcode based on all fields
        return Objects.hash(filmId, title, description, languageId, originalLanguageId, rentalDuration, rentalRate, length, replacementCost, rating, specialFeatures, lastUpdate);
    }

    // Getter for actors field
    public List<Actor> getActors() {
        return actors;
    }

    // Setter for actors field
    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
}
