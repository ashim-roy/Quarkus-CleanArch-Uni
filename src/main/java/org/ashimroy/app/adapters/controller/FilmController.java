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
public class FilmController { // controller is inside Adapter Layer calling the UseCases/Interactors in APplictaion Layer

    @Inject
    IGetFilmById getFilmByIdUseCase;

    @Inject
    IGetFilmsStartingWith getFilmsStartingWithUseCase;

    @Inject
    IGetFilmsWithLengthGreaterThan getFilmsWithLengthGreaterThanUseCase;

    @Inject
    IGetPagedFilms getPagedFilmsUseCase;

    @Inject
    IUpdateRentalRate updateRentalRateUseCase;

    // Endpoint to test the service
    @GET
    @Path("/helloAshim")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello Ashim!";
    }

    @GET
    @Path("/film/{filmId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> getFilm(@PathParam("filmId") Long filmId) {
        return getFilmByIdUseCase.execute(filmId)
                .map(film -> Response.ok(film).build())
                .onFailure(NotFoundException.class)
                .recoverWithItem(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("/filmsStartingWith/{startsWith}/{minLength}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> getFilmsStartingWith(@PathParam("startsWith") String startsWith, @PathParam("minLength") Short minLength) {
        return getFilmsStartingWithUseCase.execute(new Pair<>(startsWith, minLength))
                .map(films -> Response.ok(films).build())
                .onFailure(NotFoundException.class)
                .recoverWithItem(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("/filmsWithLengthGreaterThan/{minLength}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> getFilmsWithLengthGreaterThan(@PathParam("minLength") Short minLength) {
        return getFilmsWithLengthGreaterThanUseCase.execute(minLength)
                .map(films -> Response.ok(films).build())
                .onFailure(NotFoundException.class)
                .recoverWithItem(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("/pagedFilms/{page}/{pageSize}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> getPagedFilms(@PathParam("page") Integer page, @PathParam("pageSize") Integer pageSize) {
        return getPagedFilmsUseCase.execute(new Pair<>(page, pageSize))
                .map(films -> Response.ok(films).build())
                .onFailure(NotFoundException.class)
                .recoverWithItem(Response.status(Response.Status.NOT_FOUND).build());
    }

    @PUT
    @Path("/updateRentalRate/{minLength}/{rentalRate}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> updateRentalRate(@PathParam("minLength") Short minLength, @PathParam("rentalRate") Float rentalRate) {
        return updateRentalRateUseCase.execute(new Pair<>(minLength, rentalRate))
                .map(success -> success ? Response.ok("Update successful").build() : Response.status(Response.Status.BAD_REQUEST).build());
    }
}