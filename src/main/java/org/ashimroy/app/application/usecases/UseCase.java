package org.ashimroy.app.application.usecases;

public interface UseCase<I, O> {
    O execute(I input);
}
