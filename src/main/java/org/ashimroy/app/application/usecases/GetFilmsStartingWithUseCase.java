package org.ashimroy.app.application.usecases;
/*
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
*/


import javax.enterprise.context.ApplicationScoped;

import org.ashimroy.app.domain.model.Film;
import org.javatuples.Pair;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class GetFilmsStartingWithUseCase implements IGetFilmsStartingWith {

    @Override
    public Uni<List<Film>> execute(Pair<String, Short> input) {
        String startsWith = input.getValue0();
        short minLength = input.getValue1();
        if (startsWith == null || startsWith.isEmpty()) {
            return Uni.createFrom().failure(new IllegalArgumentException("Input must not be null or empty"));
        }

        return Uni.createFrom().item(() -> Film.list("title LIKE ?1 AND length > ?2", startsWith + "%", minLength))
    .onItem().transform(list -> list.stream().map(film -> (Film) film).collect(Collectors.toList()))
    .onItem().ifNull().failWith(() -> new NotFoundException("No films found"))
    .onItem().transformToUni(list -> list.isEmpty() ? Uni.createFrom().failure(new NotFoundException("No films found")) : Uni.createFrom().item(list));
    }
} 