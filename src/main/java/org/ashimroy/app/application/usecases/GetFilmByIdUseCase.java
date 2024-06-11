package org.ashimroy.app.application.usecases;

import org.ashimroy.app.adapters.gateways.FilmDataSource;
import org.ashimroy.app.domain.model.Film;

import io.smallrye.mutiny.Uni;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import javax.inject.Inject;


@ApplicationScoped
public class GetFilmByIdUseCase implements IGetFilmById{

    @Inject
    FilmDataSource filmDataSource;

    @Override
    public Uni<Film> execute(Short filmId) {
        return filmDataSource.getFilmById(filmId)
                .onItem().ifNull().failWith(() -> new NotFoundException("Film not found"));
    }
}
 