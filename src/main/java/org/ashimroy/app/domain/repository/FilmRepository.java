package org.ashimroy.app.domain.repository;

import org.ashimroy.app.domain.model.Film;
import io.smallrye.mutiny.Uni;

public interface FilmRepository {
    Uni<Film> getFilmById(Short id);
}
