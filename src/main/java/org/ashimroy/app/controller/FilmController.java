package org.ashimroy.app.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.stream.Collectors;

import org.ashimroy.app.service.FilmService;

import com.oracle.svm.core.annotate.Inject;

import io.smallrye.mutiny.Uni;

// to use Panache, you don't need a repository class. You can directly use the entity class to perform database operations. Here is how you can modify your FilmController to use Panache:
// The @Path annotation defines the base URI for the resource class.
// The @GET annotation defines the HTTP method to handle the request.
@Path("/")
public class FilmController {

    @Inject
    FilmService filmService;

    // Endpoint to test the service
    @GET
    @Path("/helloAshim")
    @Produces(MediaType.TEXT_PLAIN)  // The @Produces annotation is used to specify the media type of the response, which in this case is plain text (MediaType.TEXT_PLAIN).
    public String hello() {
        return "Hello Ashim!";
    }

    // Endpoint to get a film by its ID
    @GET
    @Path("/film/{filmId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<Object> getFilm(@PathParam("filmId") short filmId) {
        return filmService.getFilmById(filmId)
                .onItem().transform(film -> film != null ? film.getTitle() : "No film was found!");
    }

    // Endpoint to get a page of films with length greater than the provided minLength, The response is a string with each film's title and length, separated by a newline.
    @GET
    @Path("/pagedFilms/{page}/{minLength}")
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> paged(@PathParam("page") long page, @PathParam("minLength") short minLength) {
        if (minLength < 0 || minLength > 182 ) {
            return Uni.createFrom().failure(new IllegalArgumentException("Min length cannot be negative or more than 182 minutes"));
        }
        return filmService.getPagedFilms(page, minLength)
                .onItem().transform(films -> films.stream()
                        .map(f -> String.format("%s (%d min)", f.getTitle(), f.getLength()))
                        .collect(Collectors.joining("\n")))
                .onFailure().recoverWithItem("An error occurred while fetching paged films");
    }

    // Endpoint to get films with title starting with the provided string and length greater than the provided minLength
    @GET
    @Path("/actors/{startsWith}/{minLength}")
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> actors(@PathParam("startsWith") String startsWith, @PathParam("minLength") short minLength) {
        return filmService.getFilmsStartingWith(startsWith, minLength)
                .onItem().transform(films -> films.stream()
                        .map(f -> String.format("%s (%d min)", f.getTitle(), f.getLength()))
                        .collect(Collectors.joining(", ")));
    }

    // Endpoint to update the rental rate of films with length greater than the provided minLength

    @PUT
    @Path("/update/{minLength}/{rentalRate}")
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> update(@PathParam("minLength") short minLength, @PathParam("rentalRate") Float rentalRate) {
        return filmService.updateRentalRate(minLength, rentalRate)
                .onItem().transform(updated -> updated != null ? "Update successful" : "Update failed");
    }

}

/*
 * The FilmController class defines REST endpoints for interacting with films. Each endpoint seems to be well-defined and properly annotated with JAX-RS annotations.
Error handling and response generation are handled gracefully.
The usage of Uni in the controller methods ensures non-blocking behavior, which is suitable for reactive applications.
The controller methods interact with the FilmService class to perform business logic and database operations.
The controller methods transform the results of database operations into appropriate response formats.
The controller methods use the @PathParam annotation to extract values from the request URI.
The controller methods use the @Produces annotation to specify the media type of the response.
The controller methods use the @GET and @PUT annotations to define the HTTP methods for handling requests.

 */