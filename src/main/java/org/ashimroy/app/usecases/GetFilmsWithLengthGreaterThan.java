package org.ashimroy.app.usecases;

import org.ashimroy.app.model.Film;
import io.smallrye.mutiny.Uni;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import java.util.List;
/*
import org.javatuples.Pair;
import java.time.Duration;
import java.util.ArrayList;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
*/

@ApplicationScoped
public class GetFilmsWithLengthGreaterThan implements UseCase<Short, Uni<List<Film>>> {

    @Override
    public Uni<List<Film>> execute(Short input) {
        if (input < 0) {
            return Uni.createFrom().failure(new IllegalArgumentException("Length must be positive"));
        }

        return Uni.createFrom().item(() -> Film.<Film>list("length > ?1", input))
            .onItem().ifNull().failWith(() -> new NotFoundException("No films found"));
    }
}