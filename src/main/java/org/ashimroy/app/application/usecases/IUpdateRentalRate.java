package org.ashimroy.app.application.usecases;

import io.smallrye.mutiny.Uni;
import org.javatuples.Pair;


public interface IUpdateRentalRate extends UseCase<Pair<Short, Float>, Uni<Boolean>> {}
    

