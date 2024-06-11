package org.ashimroy.app.application.usecases;

import io.smallrye.mutiny.Uni;

import org.ashimroy.app.domain.model.Film;
import org.javatuples.Pair;
import java.util.List;

public interface IGetPagedFilms extends UseCase<Pair<Long, Short>, Uni<List<Film>>> {}
