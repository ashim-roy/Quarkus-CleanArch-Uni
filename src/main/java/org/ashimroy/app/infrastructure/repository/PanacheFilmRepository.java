package org.ashimroy.app.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Uni;
import org.ashimroy.app.domain.entity.Actor;
import org.ashimroy.app.domain.entity.Film;
import org.ashimroy.app.domain.repository.FilmRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PanacheFilmRepository implements PanacheRepositoryBase<FilmEntity, Long>, FilmRepository {

    @Override
    public Uni<Film> findFilmById(Long id) {
        return Uni.createFrom().optional(findByIdOptional(id))
                .onItem().transform(this::toDomainModel);
    }

    @Override
    public Uni<List<Film>> findByTitleStartingWithAndLengthGreaterThan(String startsWith, Short minLength) {
        return Uni.createFrom().item(() -> list("title LIKE ?1 AND length > ?2", startsWith + "%", minLength))
                .onItem().transform(list -> list.stream().map(this::toDomainModel).collect(Collectors.toList()));
    }

    @Override
    public Uni<List<Film>> findAllPaged(int page, int pageSize) {
        return Uni.createFrom().item(() -> findAll().page(page, pageSize).list())
                .onItem().transform(list -> list.stream().map(this::toDomainModel).collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public Uni<Integer> updateRentalRateForFilmsWithLengthGreaterThan(Short minLength, Float rentalRate) {
        return Uni.createFrom().item(() -> update("rentalRate = ?1 where length > ?2", rentalRate, minLength));
    }

    @Override
    public Uni<List<Film>> findByLengthGreaterThan(Short length) { // Implement this method
        return Uni.createFrom().item(() -> list("length > ?1", length))
                .onItem().transform(list -> list.stream().map(this::toDomainModel).collect(Collectors.toList()));
    }

    private Film toDomainModel(FilmEntity entity) {
        if (entity == null) {
            return null;
        }
        Film film = new Film();
        film.setId(entity.getId());
        film.setTitle(entity.getTitle());
        film.setDescription(entity.getDescription());
        film.setLanguageId(entity.getLanguageId());
        film.setOriginalLanguageId(entity.getOriginalLanguageId());
        film.setRentalDuration(entity.getRentalDuration());
        film.setRentalRate(entity.getRentalRate());
        film.setLength(entity.getLength());
        film.setReplacementCost(entity.getReplacementCost());
        film.setRating(entity.getRating());
        film.setSpecialFeatures(entity.getSpecialFeatures());
        film.setLastUpdate(entity.getLastUpdate());
        film.setActors(toDomainModelActors(entity.getActors()));
        return film;
    }

    private List<Film> toDomainModel(List<FilmEntity> entities) {
        return entities.stream()
                .map(this::toDomainModel)
                .collect(Collectors.toList());
    }

    private Actor toDomainModelActor(ActorEntity entity) {
        if (entity == null) {
            return null;
        }
        Actor actor = new Actor();
        actor.setId(entity.getId());
        actor.setFirstName(entity.getFirstName());
        actor.setLastName(entity.getLastName());
        actor.setLastUpdate(entity.getLastUpdate());
        actor.setFilms(new HashSet<>(toDomainModel(entity.getFilms())));
        return actor;
    }

    private List<Actor> toDomainModelActors(List<ActorEntity> entities) {
        return entities.stream()
                .map(this::toDomainModelActor)
                .collect(Collectors.toList());
    }
}