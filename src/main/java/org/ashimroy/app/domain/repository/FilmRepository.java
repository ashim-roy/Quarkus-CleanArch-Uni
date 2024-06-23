package org.ashimroy.app.domain.repository;

import io.smallrye.mutiny.Uni;
import java.util.List;
import org.ashimroy.app.domain.entity.Film;

public interface FilmRepository { // FilmRepository: Interface defining data access methods for Film.
    Uni<Film> findFilmById(Long id);
    Uni<List<Film>> findByTitleStartingWithAndLengthGreaterThan(String startsWith, Short minLength);
    Uni<List<Film>> findAllPaged(int page, int pageSize);
    Uni<Integer> updateRentalRateForFilmsWithLengthGreaterThan(Short minLength, Float rentalRate);
    Uni<List<Film>> findByLengthGreaterThan(Short length);
}