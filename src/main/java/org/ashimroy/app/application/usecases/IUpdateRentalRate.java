package org.ashimroy.app.application.usecases;

import io.smallrye.mutiny.Uni;
import org.javatuples.Pair;

public interface IUpdateRentalRate {
    Uni<Boolean> execute(Pair<Short, Float> input);
}
    

