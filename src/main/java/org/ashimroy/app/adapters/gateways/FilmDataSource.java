package org.ashimroy.app.adapters.gateways;

import org.ashimroy.app.domain.model.Film;
import io.smallrye.mutiny.Uni;

public interface FilmDataSource {
    Uni<Film> getFilmById(Short filmId);
}
