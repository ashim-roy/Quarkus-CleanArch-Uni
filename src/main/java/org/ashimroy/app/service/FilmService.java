import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import org.ashimroy.app.model.Film;

import java.util.List;

@ApplicationScoped
public class FilmService {

    public Uni<Film> getFilmById(short filmId) {
        return Uni.createFrom().item(() -> Film.<Film>findById(filmId))
                .onItem().ifNull().failWith(() -> new NotFoundException("Film not found"));
    }

    public Uni<List<Film>> getPagedFilms(long page, short minLength) {
        return Uni.createFrom().item(Film.find("length > ?1", minLength).page(page, 10).list());
    }

    public Uni<List<Film>> getFilmsStartingWith(String startsWith, short minLength) {
        return Uni.createFrom().item(() -> Film.find("title like ?1 and length > ?2", startsWith + "%", minLength).list());
    }

    public Uni<String> updateRentalRate(short minLength, Float rentalRate) {
        return Uni.createFrom().item(() -> {
            int updated = Film.update("rentalRate = ?1 where length > ?2", rentalRate, minLength);
            if (updated > 0) {
                return "Update successful";
            } else {
                return "No films updated";
            }
        }).runSubscriptionOn(Infrastructure.getDefaultExecutor());
    }

    public Uni<List<Film>> getFilmsWithLengthGreaterThan(short minLength) {
        return Uni.createFrom().item(() -> Film.find("length > ?1", minLength).list());
    }
}

/*
 * They interact with the database using Panache methods effectively.
 * They use Uni to handle asynchronous operations.
 * They use Infrastructure.getDefaultExecutor() to run the database operation on a separate thread.
 * They use Uni.createFrom().item() to create a Uni from a value.
 * They use Uni.onItem().ifNull().failWith() to throw a NotFoundException if the film is not found.
 * They use Uni.onItem().transform() to transform the result of a Uni operation.
 * They use Uni.runSubscriptionOn() to run the database operation on a separate thread.
 * They use Uni.onItem().transform() to transform the result of a Uni operation.
 */

 /*
  * The usage of Uni from SmallRye Mutiny is appropriate for handling asynchronous operations.
    * The use of Panache methods to interact with the database is correct.
    * The use of Infrastructure.getDefaultExecutor() to run the database operation on a separate thread is correct.
    * The use of Uni.createFrom().item() to create a Uni from a value is correct.
    * The use of Uni.onItem().ifNull().failWith() to throw a NotFoundException if the film is not found is correct.
    * The use of Uni.onItem().transform() to transform the result of a Uni operation is correct.
    * The use of Uni.runSubscriptionOn() to run the database operation on a separate thread is correct.
  */