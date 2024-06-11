
![image](https://github.com/ashim-roy/Quarkus-CleanArch-Uni/assets/118077929/16b340b0-a52e-4867-93b6-7da1aed5e4ff)


Domain Layer:

    org.ashimroy.app.domain.model: Contains core business objects like Actor and Film.
    org.ashimroy.app.domain.repository: Contains FilmRepository (interface) and its implementation PanacheFilmRepository.

Application Layer:

    org.ashimroy.app.application.usecases: Contains various use cases and their respective interfaces, ensuring use cases depend on abstraction(interfaces) rather than concrete implementations.
    Each use case class (e.g., GetFilmByIdUseCase) implements its respective interface (e.g., IGetFilmById) and uses the FilmRepository interface.

Adapters Layer:

    org.ashimroy.app.adapters.controllers: Contains the FilmController, which interacts with the use cases.
    org.ashimroy.app.adapters.gateways: Contains FilmDataSource (interface), which defines methods for data access.

This structure promotes a clear separation of concerns, adheres to the Dependency Inversion principle, and ensures that your use cases are decoupled from specific implementations of the repository. It also makes the codebase more maintainable and testable.

