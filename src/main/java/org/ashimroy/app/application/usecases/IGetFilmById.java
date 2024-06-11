package org.ashimroy.app.application.usecases;
import org.ashimroy.app.domain.model.Film;

import io.smallrye.mutiny.Uni;

public interface IGetFilmById {
    Uni<Film> execute(Short filmId);
}
