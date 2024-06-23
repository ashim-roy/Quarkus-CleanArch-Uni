package org.ashimroy.app.application.usecases;

import io.smallrye.mutiny.Uni;
import javax.enterprise.context.ApplicationScoped;
import org.ashimroy.app.domain.entity.Film;
import org.ashimroy.app.domain.repository.FilmRepository;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class GetFilmsWithLengthGreaterThanUseCase implements IGetFilmsWithLengthGreaterThan {

    private final FilmRepository filmRepository;

    @Inject
    public GetFilmsWithLengthGreaterThanUseCase(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public Uni<List<Film>> execute(Short input) {
        if (input < 0) {
            return Uni.createFrom().failure(new IllegalArgumentException("Length must be positive"));
        }
        return filmRepository.findByLengthGreaterThan(input);
    }
}