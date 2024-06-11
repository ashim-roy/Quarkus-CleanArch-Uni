package org.ashimroy.app.adapters.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import javax.ws.rs.NotFoundException;

import org.ashimroy.app.application.usecases.IGetFilmById;
import org.ashimroy.app.application.usecases.IGetFilmsStartingWith;
import org.ashimroy.app.application.usecases.IGetFilmsWithLengthGreaterThan;
import org.ashimroy.app.application.usecases.IGetPagedFilms;
import org.ashimroy.app.application.usecases.IUpdateRentalRate;
import org.javatuples.Pair;
import io.smallrye.mutiny.Uni;
import javax.inject.Inject;

@Path("/")
public class FilmController {

    @Inject
    IGetFilmById getFilmByIdUseCase;

    @Inject
    IGetFilmsStartingWith getFilmsStartingWith;

    @Inject
    IGetFilmsWithLengthGreaterThan getFilmsWithLengthGreaterThan;

    @Inject
    IGetPagedFilms getPagedFilmsUseCase;

    @Inject
    IUpdateRentalRate updateRentalRate;

    // Endpoint to test the service
    @GET
    @Path("/helloAshim")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello Ashim!";
    }

    // Endpoint to get a film by its ID
    @GET
    @Path("/film/{filmId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> getFilm(@PathParam("filmId") short filmId) {
        return getFilmByIdUseCase.execute(filmId)
                .onItem().transform(film -> {
                    if (film != null) {
                        return Response.ok(film).build();
                    } else {
                        throw new NotFoundException("No film was found!");
                    }
                })
                .onFailure().recoverWithItem(e -> Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build());
    }

    // Endpoint to get a page of films with length greater than the provided minLength
    @GET
    @Path("/pagedFilms/{page}/{minLength}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> paged(@PathParam("page") long page, @PathParam("minLength") short minLength) {
        if (minLength < 0 || minLength > 182) {
            return Uni.createFrom().failure(new IllegalArgumentException("Min length cannot be negative or more than 182 minutes"))
                    .onItem().transform(e -> Response.status(Response.Status.BAD_REQUEST).entity(((Throwable)e).getMessage()).build());
        }
        return getPagedFilmsUseCase.execute(new Pair<>(page, minLength))
                .onItem().transform(films -> {
                    if (!films.isEmpty()) {
                        return Response.ok(films).build();
                    } else {
                        throw new NotFoundException("No films found with the given criteria!");
                    }
                })
                .onFailure().recoverWithItem(e -> Response.status(Response.Status.NOT_FOUND).entity(((Throwable)e).getMessage()).build());
    }

    // Endpoint to get films with title starting with the provided string and length greater than the provided minLength
    @GET
    @Path("/filmsStartingWith/{startsWith}/{minLength}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> filmsStartingWith(@PathParam("startsWith") String startsWith, @PathParam("minLength") short minLength) {
        return getFilmsStartingWith.execute(new Pair<>(startsWith, minLength))
                .onItem().transform(films -> {
                    if (!films.isEmpty()) {
                        return Response.ok(films).build();
                    } else {
                        throw new NotFoundException("No films found with the given criteria!");
                    }
                })
                .onFailure().recoverWithItem(e -> Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build());
    }

    // Endpoint to update the rental rate of films with length greater than the provided minLength
    @PUT
    @Path("/update/{minLength}/{rentalRate}")
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> update(@PathParam("minLength") short minLength, @PathParam("rentalRate") Float rentalRate) {
        return updateRentalRate.execute(new Pair<>(minLength, rentalRate))
                .onItem().transform(updated -> updated ? "Update successful" : "Update failed");
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