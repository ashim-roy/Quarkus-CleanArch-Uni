package org.ashimroy.app.application.usecases;

import io.smallrye.mutiny.Uni;
import java.util.List;
import org.ashimroy.app.domain.entity.Film;
import org.javatuples.Pair;

public interface IGetPagedFilms {
    Uni<List<Film>> execute(Pair<Integer, Integer> input);
}