package org.ashimroy.app.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.ashimroy.app.model.Film;
import org.ashimroy.app.repository.FilmRepository;
import org.ashimroy.app.model.Actor; 


// The @Path annotation defines the base URI for the resource class.
@Path("/")
public class FilmController {
    
    // The @Inject annotation is used to inject the FilmRepository dependency.
    @Inject
    FilmRepository filmRepository; 
    
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
    @Produces(MediaType.TEXT_PLAIN)
    public String getFilm(@PathParam("filmId") short filmId) {
        Optional<Film> film = filmRepository.getFilm(filmId);
        return film.isPresent() ? film.get().getTitle() : "No film was found!";
    }
    
    // Endpoint to get a page of films with length greater than the provided minLength
    @GET
    @Path("/pagedFilms/{page}/{minLength}")
    @Produces(MediaType.TEXT_PLAIN) 
    public String paged(@PathParam("page") long page, @PathParam("minLength") short minLength) {
        return filmRepository.paged(page, minLength)
                .map(f -> String.format("%s (%d min)", f.getTitle(), f.getLength()))
                .collect(Collectors.joining("\n"));
    }

    // Endpoint to get films with title starting with the provided string and length greater than the provided minLength
    @GET
    @Path("/actors/{startsWith}/{minLength}")
    @Produces(MediaType.TEXT_PLAIN)
    public String actors(@PathParam("startsWith") String startsWith, @PathParam("minLength") short minLength) {
        return filmRepository.actors(startsWith, minLength)
                .map(f -> String.format("%s (%d min)", f.getTitle(), f.getLength()))
                .collect(Collectors.joining(", "));
    }


    // Endpoint to update the rental rate of films with length greater than the provided minLength
    @GET
    @Path("/update/{minLength}/{rentalRate}")
    @Produces(MediaType.TEXT_PLAIN)
    public String update(@PathParam("minLength") short minLength, @PathParam("rentalRate") Float rentalRate) {
        filmRepository.updateRentalRate(minLength, rentalRate);
        return filmRepository.getFilms(minLength)
                .map(f -> String.format("%s (%d min) - $%f", f.getTitle(), f.getLength(), f.getRentalRate()))
                .collect(Collectors.joining("\n"));
    }
    
}