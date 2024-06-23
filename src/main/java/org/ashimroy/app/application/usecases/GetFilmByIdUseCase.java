package org.ashimroy.app.application.usecases;

import io.smallrye.mutiny.Uni;
import org.ashimroy.app.domain.entity.Film;
import org.ashimroy.app.domain.repository.FilmRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class GetFilmByIdUseCase implements IGetFilmById {

    @Inject
    FilmRepository filmRepository;

    @Override
    public Uni<Film> execute(Long filmId) {
        return filmRepository.findFilmById(filmId);
    }
}

    // if you have separated your domain model (Film) from your persistence model (FilmEntity), this kind of conversion 
    // could be necessary when you fetch data from the database and want to convert it into your domain model.
