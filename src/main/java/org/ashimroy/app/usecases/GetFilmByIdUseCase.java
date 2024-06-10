package org.ashimroy.app.usecases;

import org.ashimroy.app.model.Film;
import io.smallrye.mutiny.Uni;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
//import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



@ApplicationScoped
public class GetFilmByIdUseCase implements UseCase<Short, Uni<Film>> {

    @PersistenceContext
    EntityManager em;

    @Override
    public Uni<Film> execute(Short filmId) {
        return Uni.createFrom().item(() -> em.find(Film.class, filmId))
                .onItem().ifNull().failWith(() -> new NotFoundException("Film not found"));
    }
}
 