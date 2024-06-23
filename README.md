
The project file is a Quarkus-based application that follows the Clean Architecture principles. It is structured into four main layers: Domain, Application, Infra and Adapters, promoting a clear separation of concerns, maintainability, and testability. Here's a summary of each layer and its components:

Domain Layer
    Core Business Objects: Located under org.ashimroy.app.domain.model, this package contains core business entities such as Actor and Film.
    Repository Interfaces: The org.ashimroy.app.domain.repository package includes interfaces for repositories, like FilmRepository, and its implementation PanacheFilmRepository, which interact with the database.
    org.ashimroy.app.domain.model: Contains core business objects like Actor and Film.
    org.ashimroy.app.domain.repository: Contains FilmRepository (interface) and its implementation PanacheFilmRepository.

Application Layer
    Use Cases and Interfaces: The org.ashimroy.app.application.usecases package houses various use cases and their interfaces, ensuring that these use cases rely on abstractions (interfaces) rather than concrete implementations. For example, the GetFilmByIdUseCase implements the IGetFilmById interface and utilizes the FilmRepository interface for data operations.

    org.ashimroy.app.application.usecases: Contains various use cases and their respective interfaces, ensuring use cases depend on abstraction(interfaces) rather than concrete implementations.
    Each use case class (e.g., GetFilmByIdUseCase) implements its respective interface (e.g., IGetFilmById) and uses the FilmRepository interface.


Adapters Layer
    Controllers: Found in org.ashimroy.app.adapters.controllers, this package contains controllers like FilmController, which serve as the entry point for interacting with the application's use cases.
    Data Access Gateways: The org.ashimroy.app.adapters.gateways package includes interfaces for data access, such as FilmDataSource, defining methods for data operations.

    org.ashimroy.app.adapters.controllers: Contains the FilmController, which interacts with the use cases.
    org.ashimroy.app.adapters.gateways: Contains FilmDataSource (interface), which defines methods for data access.

Overall, this project structure adheres to the Dependency Inversion principle, ensuring that higher-level modules are not dependent on lower-level modules but rather on abstractions. This approach decouples use cases from specific implementations of repositories and data sources, enhancing the codebase's maintainability and testability.


This structure promotes a clear separation of concerns, adheres to the Dependency Inversion principle, and ensures that your use cases are decoupled from specific implementations of the repository. It also makes the codebase more maintainable and testable.

Infrastructure layer:
The Infrastructure layer in this Quarkus-based application serves as the bridge between the application's core domain and the external systems or frameworks, such as databases. It primarily consists of entity models and repository implementations that interact with the database. Here's an overview of the components within the Infrastructure layer:

Entity Models
    ActorEntity, FilmEntity, CategoryEntity: These are Java Persistence API (JPA) entities that map the application's domain objects to database tables. Each entity corresponds to a specific table in the database and includes annotations to define relationships, primary keys, and other JPA-specific configurations. These entities allow the application to perform CRUD operations on the database tables they represent.
PanacheFilmRepository
    Implements PanacheRepositoryBase: This repository implementation uses Quarkus's Panache, a library that simplifies Hibernate ORM with active record or repository patterns. It extends PanacheRepositoryBase<FilmEntity, Long>, indicating it operates on FilmEntity objects with Long type identifiers.
    Implements FilmRepository Interface: By implementing the FilmRepository interface from the domain layer, PanacheFilmRepository adheres to the Dependency Inversion Principle, allowing the application layer to depend on abstractions rather than concrete implementations.
    Database Operations: It provides implementations for various database operations declared in the FilmRepository interface, such as findFilmById, findByTitleStartingWithAndLengthGreaterThan, findAllPaged, updateRentalRateForFilmsWithLengthGreaterThan, and findByLengthGreaterThan. These methods use Panache's simplified query mechanism to interact with the database.
    Transactional Operations: The updateRentalRateForFilmsWithLengthGreaterThan method is annotated with @Transactional, ensuring that the operation is executed within a transaction. This is crucial for maintaining data integrity during update operations.
    Domain Model Transformation: Methods like toDomainModel and toDomainModelActors are used to convert FilmEntity and ActorEntity objects to their corresponding domain models (Film and Actor). This transformation is essential for separating the domain layer from the infrastructure layer, allowing the core application logic to remain agnostic of the database implementation.

Overall, the Infrastructure layer in this application encapsulates the technical details of data persistence and provides a clean separation from the domain logic. By following the Repository pattern and using Quarkus's Panache, the application achieves a balance between simplicity and flexibility in managing database operations.


Clean Architetcure:

The principle that the internal module/layer cannot depend on an outer layer, but the outer layer can depend on the internal layer, is a core tenet of the Clean Architecture and the Dependency Inversion Principle (DIP). This approach structures an application into layers with clear responsibilities and dependencies that flow inwards. The most common layers, from outer to inner, are:

Infrastructure Layer: Deals with external concerns like databases, file systems, and web frameworks.
Application Layer: Contains application-specific logic and orchestrates the flow of data between the domain and the infrastructure.
Domain Layer: Contains the business logic, entities, and domain-specific rules.
How It Helps Achieve Clean Architecture
Decoupling: By ensuring that dependencies flow inwards, the core of the application (domain layer) remains independent of external frameworks and libraries. This decoupling makes the domain layer pure, focused solely on business rules, and unaffected by changes in external systems or technologies.
Flexibility: It allows the outer layers to be easily replaced or modified without affecting the domain layer. For example, you can switch databases or integrate new external services with minimal impact on the core application logic.
Testability: The domain layer can be tested in isolation, ensuring that the business rules work as expected without the need for external resources like databases or web servers. This isolation simplifies unit testing and improves test coverage.
Easy Integration with Third-Party Systems
Abstraction: The application layer acts as a mediator between the domain and infrastructure layers. By relying on interfaces (abstractions) rather than concrete implementations, it's easier to integrate third-party systems or libraries. For instance, if the application needs to switch to a different database provider, only the infrastructure layer that interacts with the database needs to change; the domain and application layers remain untouched.
Adapters: The outer layers often contain adapters that convert data from the format used by external systems (e.g., database rows) into the domain model used by the application, and vice versa. This conversion allows for seamless integration with third-party systems without polluting the domain layer with external concerns.
In summary, this architectural approach enhances maintainability, scalability, and the ability to adapt to new technologies or business requirements, making it a powerful strategy for building complex, long-lived applications.