package org.ashimroy.app.application.usecases;

import io.smallrye.mutiny.Uni;
import org.ashimroy.app.domain.entity.Film;
import org.javatuples.Pair;

import java.util.List;

public interface IGetFilmsStartingWith {
    Uni<List<Film>> execute(Pair<String, Short> input);
}