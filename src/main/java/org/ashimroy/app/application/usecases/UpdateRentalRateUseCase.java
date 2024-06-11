package org.ashimroy.app.application.usecases;

import io.smallrye.mutiny.Uni;
import org.ashimroy.app.data.entities.Film;
import org.javatuples.Pair;
import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;


@ApplicationScoped
public class UpdateRentalRateUseCase implements IUpdateRentalRate{

    @Override
    public Uni<Boolean> execute(Pair<Short, Float> input) {
        if (input.getValue0() < 0 || input.getValue1() < 0) {
            return Uni.createFrom().failure(new IllegalArgumentException("Film length and rental rate must be positive"));
        }

        return Uni.createFrom().item(() -> 
                Film.update("rental_rate = ?1 where length > ?2", BigDecimal.valueOf(input.getValue1()), input.getValue0()))
                .onItem().transform(updated -> updated > 0)
                .onFailure().recoverWithItem(false);
    }
}