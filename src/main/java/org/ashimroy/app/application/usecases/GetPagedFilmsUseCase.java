package org.ashimroy.app.application.usecases;

import io.smallrye.mutiny.Uni;

import org.ashimroy.app.domain.model.Film;
import org.javatuples.Pair;
//import javax.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import io.quarkus.panache.common.Page;
import java.util.List;
import java.util.stream.Collectors;
import java.time.Duration; // import for Duration
import java.util.ArrayList; // import for ArrayList
//import io.quarkus.hibernate.orm.panache.PanacheEntityBase; // import for PanacheEntityBase
import javax.enterprise.context.ApplicationScoped;
//import javax.ws.rs.NotFoundException;


@ApplicationScoped
public class GetPagedFilmsUseCase implements IGetPagedFilms {

    @Override
    public Uni<List<Film>> execute(Pair<Long, Short> input) {
        if (input.getValue0() < 0 || input.getValue1() < 0) {
            return Uni.createFrom().failure(new IllegalArgumentException("Page number and length must be positive"));
        }

        return Uni.createFrom().item(() -> 
        Film.findAll().page(Page.of(input.getValue0().intValue(), input.getValue1().intValue())).list().stream().map(f -> (Film) f).collect(Collectors.toList()))
        .onItem().ifNull().failWith(() -> new NotFoundException("No films found"))
        .ifNoItem().after(Duration.ofSeconds(10)).fail()
        .onFailure().recoverWithItem(new ArrayList<>());
    }
}