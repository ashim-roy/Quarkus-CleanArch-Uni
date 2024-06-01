package org.ashimroy.app.repository;

import com.speedment.jpastreamer.application.JPAStreamer;
import com.speedment.jpastreamer.projection.Projection;
import com.speedment.jpastreamer.streamconfiguration.StreamConfiguration;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.ashimroy.app.model.Film;
import org.ashimroy.app.model.Film$;

import java.util.Optional;
import java.util.stream.Stream;

// The @ApplicationScoped annotation indicates that this bean is application scoped. meaning it will be created once per application and shared across all requests and sessions.
@ApplicationScoped
public class FilmRepository {
    
    // The @Inject annotation is used to inject the JPAStreamer dependency. which is a library that facilitates streaming over JPA entities.
    @Inject
    JPAStreamer jpaStreamer; 
    
    // Constant for page size
    private static final int PAGE_SIZE = 20;  // PAGE_SIZE: A constant defining the number of records per page for pagination purposes.
    
    // Method to get a film by its ID
    public Optional<Film> getFilm(short filmId) {
        return jpaStreamer.stream(Film.class)
                .filter(Film$.filmId.equal(filmId))
                .findFirst();
    }

    // Method to get films with length greater than the provided minLength
    public Stream<Film> getFilms(short minLength) {
        return jpaStreamer.stream(Film.class)
                .filter(Film$.length.greaterThan(minLength))
                .sorted(Film$.length);
    }
    
    // Method to get a page of films with length greater than the provided minLength
    public Stream<Film> paged(long page, short minLength) {
        return jpaStreamer.stream(Film.class)
                .filter(Film$.length.greaterThan(minLength))
                .sorted(Film$.length)
                .skip(page * PAGE_SIZE)
                .limit(PAGE_SIZE);
    }
    
    // Method to get films with title starting with the provided string and length greater than the provided minLength
    //Implementation: Uses StreamConfiguration to join Film with its actors. Filters films whose titles start with the specified string and whose length is greater than minLength, then sorts the results in descending order by length.
    public Stream<Film> actors(String startsWith, short minLength) {
        final StreamConfiguration<Film> sc = 
                StreamConfiguration.of(Film.class).joining(Film$.actors);
        return jpaStreamer.stream(sc)
                .filter(Film$.title.startsWith(startsWith).and(Film$.length.greaterThan(minLength)))
                .sorted(Film$.length.reversed());
    }
    
    // Method to update the rental rate of films with length greater than the provided minLength
    // The @Transactional annotation indicates that this method should be run within a transaction context.
    // Streams over Film entities, filters them by length greater than minLength, and updates their rental rate within a transactional context.
    @Transactional
    public void updateRentalRate(short minLength, Float rentalRate) {
        jpaStreamer.stream(Film.class)
                .filter(Film$.length.greaterThan(minLength))
                .forEach(f -> {
                    f.setRentalRate(rentalRate);
                });
    }
    
}
