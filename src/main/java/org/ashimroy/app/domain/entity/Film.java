package org.ashimroy.app.domain.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Film {  // Java POJO that represents a Film entity
    public Long id;
    public String title;
    private String description;
    private short languageId;
    private Short originalLanguageId;
    private short rentalDuration;
    public Float rentalRate;
    public Short length;
    private BigDecimal replacementCost;
    private String rating;
    private String specialFeatures;
    private Timestamp lastUpdate;
    private List<Actor> actors;

    // Getters and setters for all fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
}