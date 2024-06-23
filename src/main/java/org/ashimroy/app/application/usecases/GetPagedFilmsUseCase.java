package org.ashimroy.app.application.usecases;

import io.smallrye.mutiny.Uni;
import org.ashimroy.app.domain.entity.Film;
import org.ashimroy.app.domain.repository.FilmRepository;
import org.javatuples.Pair;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class GetPagedFilmsUseCase implements IGetPagedFilms {

    private final FilmRepository filmRepository;

    @Inject
    public GetPagedFilmsUseCase(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public Uni<List<Film>> execute(Pair<Integer, Integer> input) { // Adjusted to accept Pair<Integer, Integer>
        int page = input.getValue0();
        int pageSize = input.getValue1();

        return filmRepository.findAllPaged(page, pageSize);
    }
}

/*
 * Used In: paged method
Endpoint: /pagedFilms/{page}/{minLength}
Description: This use case is executed when the /pagedFilms/{page}/{minLength} endpoint is called. It retrieves a page of films with a length greater than the specified minimum length.
 */