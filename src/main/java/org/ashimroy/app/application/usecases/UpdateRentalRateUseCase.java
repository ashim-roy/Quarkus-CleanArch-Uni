package org.ashimroy.app.application.usecases;

import io.smallrye.mutiny.Uni;
import org.javatuples.Pair;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.ashimroy.app.domain.repository.FilmRepository;

@ApplicationScoped
public class UpdateRentalRateUseCase implements IUpdateRentalRate {

    private final FilmRepository filmRepository;

    @Inject
    public UpdateRentalRateUseCase(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public Uni<Boolean> execute(Pair<Short, Float> input) {
        if (input.getValue0() < 0 || input.getValue1() < 0) {
            return Uni.createFrom().failure(new IllegalArgumentException("Film length and rental rate must be positive"));
        }

        return filmRepository.updateRentalRateForFilmsWithLengthGreaterThan(input.getValue0(), input.getValue1())
            .onItem().transform(updated -> updated > 0);
    }
}

/*
 * Used In: update method
Endpoint: /update/{minLength}/{rentalRate}
Description: This use case is executed when the /update/{minLength}/{rentalRate} endpoint is called. 
It updates the rental rate of films with a length greater than the specified minimum length.
 */