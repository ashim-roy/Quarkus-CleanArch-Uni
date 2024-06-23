/*
package org.ashimroy.app.domain.repository;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import io.smallrye.mutiny.Uni;
import org.ashimroy.app.domain.entity.Film;
import org.ashimroy.app.infrastructure.repository.FilmEntity;
import org.ashimroy.app.infrastructure.repository.PanacheFilmRepository;

@ApplicationScoped
public class FilmRepositoryImpl implements FilmRepository {

    private final PanacheFilmRepository panacheFilmRepository;

    @Inject
    public FilmRepositoryImpl(PanacheFilmRepository panacheFilmRepository) {
        this.panacheFilmRepository = panacheFilmRepository;
    }

    @Override
    public Uni<Film> findFilmById(Long filmId) {
    return panacheFilmRepository.findFilmById(filmId)
        .onItem().transform(this::convertToFilm);  // irectly calls the findFilmById method in PanacheFilmRepository.
    }
    // Method to convert list of FilmEntity to list of Film
    private List<Film> toDomainModel(List<FilmEntity> entities) {
        return entities.stream()
                .map(this::toDomainModel)
                .collect(Collectors.toList());
    }

}
*/