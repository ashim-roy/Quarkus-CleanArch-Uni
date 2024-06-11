package org.ashimroy.app.application.usecases;

import io.smallrye.mutiny.Uni;
import java.util.List;

import org.ashimroy.app.domain.model.Film;

public interface IGetFilmsWithLengthGreaterThan {
    Uni<List<Film>> execute(Short input);
}