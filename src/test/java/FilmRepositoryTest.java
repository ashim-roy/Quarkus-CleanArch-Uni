import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import io.smallrye.mutiny.Uni;

import org.ashimroy.app.domain.entity.Film;
import org.ashimroy.app.domain.repository.FilmRepository;
import org.junit.jupiter.api.Test;
//import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class FilmRepositoryTest {
    
    @Inject
    FilmRepository filmRepository; 
    
    @Test
    public void test() {
        Uni<Film> filmUni = filmRepository.getFilmById((short) 5); 
        Film film = filmUni.await().indefinitely();
        assertNotNull(film);
        assertEquals("AFRICAN EGG", film.getTitle());
    }
}
