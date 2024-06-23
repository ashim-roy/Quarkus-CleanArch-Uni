/* 
package org.ashimroy.app.adapters.gateways;

import org.ashimroy.app.domain.entity.Film;

import io.smallrye.mutiny.Uni;

public interface FilmDataSource {
    Uni<Film> getFilmById(Short filmId);
}
*/

/*
 * FilmDataSource  This is an interface that defines the operations that your application needs from a data source. This is part of the Adapter layer in Clean Architecture. 
 * It's not concerned with business rules or use cases, but rather with the technical details of data access.
 */

/*
Purpose: Abstracts the low-level data access details, providing a contract for fetching data from the underlying data store.
Typical Methods: Methods for basic CRUD operations (e.g., getFilmById, getAllFilms, saveFilm).

 * Purpose: The FilmDataSource interface provides a clear and consistent contract for accessing film data asynchronously.
Benefits:
Decoupling: Separates the data access logic from business logic.
Flexibility: Allows for different implementations  without changing the business logic.
Asynchronous Processing: Enhances performance by enabling non-blocking, reactive data operations.
By following this pattern, the application remains modular, testable, and adaptable to changes in data access mechanisms.

 */