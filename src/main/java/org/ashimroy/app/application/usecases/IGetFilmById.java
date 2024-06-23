package org.ashimroy.app.application.usecases;
import org.ashimroy.app.domain.entity.Film;

import io.smallrye.mutiny.Uni;

public interface IGetFilmById {
    Uni<Film> execute(Long filmId);
}
