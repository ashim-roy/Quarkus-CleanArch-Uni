package org.ashimroy.app.application.usecases;

import io.smallrye.mutiny.Uni;
import org.ashimroy.app.domain.entity.Film;
import org.ashimroy.app.domain.repository.FilmRepository;
import org.javatuples.Pair;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class GetFilmsStartingWith implements IGetFilmsStartingWith {
// usecase layer is inside Application Layer and depend on filmRepository-IF which is in domain layer/business Rule/logic

    @Inject
    FilmRepository filmRepository; 

    @Override
    public Uni<List<Film>> execute(Pair<String, Short> input) {
        String startsWith = input.getValue0();
        Short minLength = input.getValue1();
        // Use the repository method to find films starting with the given title and greater than a minimum length
        return filmRepository.findByTitleStartingWithAndLengthGreaterThan(startsWith, minLength);
    }
}