package org.ashimroy.app.domain.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import io.smallrye.mutiny.Uni;
import org.ashimroy.app.adapters.gateways.FilmDataSource;
import org.ashimroy.app.domain.model.Film;


@ApplicationScoped
public class PanacheFilmDataSource implements FilmDataSource, FilmRepository {
    
    @Inject
    Film film;

    @Override
    public Uni<Film> getFilmById(Short id) {
        return Uni.createFrom().optional(film.findByIdOptional((long) id));
    }
}

/*
 * The FilmDataSource interface is acting as a contract for your data source operations, which in this case is PanacheFilmDataSource. 
 * This interface is useful when you want to switch between different data sources without changing the rest of your code.
 * 
 * The FilmRepository interface is acting as a contract for your repository operations. This is useful when you want to switch 
 * between different types of repositories (like in-memory, database, etc.) without changing the rest of your code.
 */

