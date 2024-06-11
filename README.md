org.ashimroy.app.domain
    - model
        - Actor
        - Film
    - repository
        - FilmRepository
        - PanacheFilmRepository (implements FilmRepository)

org.ashimroy.app.application
    - usecases
        - GetFilmByIdUseCase
            - implements -> IGetFilmById
            - uses -> FilmRepository
        - IGetFilmById
        - GetFilmsStartingWithUseCase
            - implements -> IGetFilmsStartingWith
            - uses -> FilmRepository
        - IGetFilmsStartingWith
        - GetFilmsWithLengthGreaterThanUseCase
            - implements -> IGetFilmsWithLengthGreaterThan
            - uses -> FilmRepository
        - IGetFilmsWithLengthGreaterThan
        - GetPagedFilmsUseCase
            - implements -> IGetPagedFilms
            - uses -> FilmRepository
        - IGetPagedFilms
        - UpdateRentalRateUseCase
            - implements -> IUpdateRentalRate
            - uses -> FilmRepository
        - IUpdateRentalRate

org.ashimroy.app.adapters
    - controllers
        - FilmController
            - uses -> GetFilmByIdUseCase
            - uses -> GetFilmsStartingWithUseCase
            - uses -> GetFilmsWithLengthGreaterThanUseCase
            - uses -> GetPagedFilmsUseCase
            - uses -> UpdateRentalRateUseCase
    - gateways
        - FilmDataSource (interface)


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

