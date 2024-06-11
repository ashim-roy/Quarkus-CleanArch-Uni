package org.ashimroy.app.application.usecases;

import io.smallrye.mutiny.Uni;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;

import org.ashimroy.app.domain.model.Film;

import java.util.List;

@ApplicationScoped
public class GetFilmsWithLengthGreaterThanUseCase implements IGetFilmsWithLengthGreaterThan {

    @Override
    public Uni<List<Film>> execute(Short input) {
        if (input < 0) {
            return Uni.createFrom().failure(new IllegalArgumentException("Length must be positive"));
        }

        return Uni.createFrom().item(() -> Film.<Film>list("length > ?1", input))
            .onItem().ifNull().failWith(() -> new NotFoundException("No films found"));
    }
}