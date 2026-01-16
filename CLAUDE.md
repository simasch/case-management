# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Vaadin/jOOQ-based case management application built on Spring Boot 4.0.1 with Java 25. The project
demonstrates how to integrate Vaadin (Java-based UI framework) with jOOQ (type-safe SQL) and includes comprehensive
testing using both Karibu Testing (browser-less unit tests) and Playwright with Mopo (end-to-end tests).

The application uses:

- **PostgreSQL** as the database
- **Flyway** for database migrations
- **Testcontainers** for both jOOQ code generation and testing
- **JWT authentication** for stateless security (better developer experience)
- **Spring Security** with BCrypt password encoding

## Build and Run Commands

### Initial Setup

Before running the application for the first time, generate the jOOQ metamodel (this spins up a Testcontainer with
PostgreSQL, runs Flyway migrations, then generates jOOQ classes):

```bash
./mvnw compile
```

### Development

Run the application with Testcontainers (requires Docker or Testcontainers Cloud running):

```bash
./mvnw spring-boot:test-run
```

Or run `TestApplication.java` from your IDE.

The application will be available at `http://localhost:8080` and will auto-launch a browser.

### Testing

Run all tests (unit tests with Karibu + integration tests with Playwright):

```bash
./mvnw verify
```

Run only unit tests:

```bash
./mvnw test
```

Run only integration tests:

```bash
./mvnw integration-test
```

Run a single test class from IDE or:

```bash
./mvnw test -Dtest=UserViewTest
```

### Code Quality

Check code formatting (enforced via Spring Java Format):

```bash
./mvnw spring-javaformat:validate
```

Apply code formatting:

```bash
./mvnw spring-javaformat:apply
```

Generate test coverage report:

```bash
./mvnw verify -Pcoverage
```

Report location: `target/site/jacoco/index.html`

### Production Build

Create production JAR with optimized frontend bundle:

```bash
./mvnw clean package -Pproduction
```

Run the production JAR:

```bash
java -jar target/case-management-1.0-SNAPSHOT.jar
```

## Architecture

### Layered Architecture

The codebase follows a strict layered architecture enforced by ArchUnit tests (ArchitectureTest.java):

1. **UI Layer** (`..ui..` packages)
    - Cannot be accessed by any other layer
    - Only layer allowed to use Vaadin components
    - Contains views, layouts, and UI components

2. **Security Layer** (`..security..` packages)
    - Can access Domain layer
    - Handles authentication/authorization configuration
    - Uses JWT tokens with stateless sessions

3. **Domain Layer** (`..domain..` packages)
    - Can be accessed by UI and Security layers only
    - Contains business logic, DAOs, and domain models
    - No Vaadin dependencies allowed

### Module Structure

The application uses a modular package structure where the `core` module provides shared functionality, and other
modules (like `user`, `greeting`, `person`) are feature modules:

- **core** module: Shared infrastructure (security, UI layout, i18n, configuration)
- Feature modules are isolated from each other and only depend on core
- This is enforced by ArchUnit tests

Key packages:

- `core.ui.layout/` - MainLayout with AppLayout, navigation, and language switching
- `core.security/` - Security configuration with JWT
- `core.domain/` - Base DAOs and domain objects
- `core.configuration/` - jOOQ and other bean configurations
- Feature modules follow the same structure (`[module].ui`, `[module].domain`)

### jOOQ Integration

jOOQ code generation is tightly integrated into the Maven build:

1. **Groovy Maven Plugin** (generate-sources phase): Starts a Testcontainer with PostgreSQL
2. **Flyway Maven Plugin** (generate-sources phase): Runs migrations from `src/main/resources/db/migration/`
3. **jOOQ Codegen Plugin** (generate-sources phase): Generates type-safe DAOs and records into
   `ch.martinelli.demo.casemanagement.db` package

Generated classes are excluded from git. Always run `./mvnw compile` after pulling migration changes.

The custom generator `ch.martinelli.oss.jooq.EqualsAndHashCodeJavaGenerator` adds equals/hashCode to generated records.

jOOQ is configured with optimistic locking enabled (VjJooqConfiguration.java).

### Data Access Pattern

This project uses a custom `JooqDAO` base class from `jooq-spring` library:

```java

@Repository
public class UserDAO extends JooqDAO<User, UserRecord, String> {
    public UserDAO(DSLContext dslContext) {
        super(dslContext, USER);
    }
    // Custom queries using DSLContext
}
```

The DAO pattern provides standard CRUD operations and allows custom queries. Records are attached to DSLContext and use
`.store()` for upsert operations.

For complex queries with joins, use jOOQ multiset for one-to-many relationships:

```java
multiset(select(...).from(...).where(...)).convertFrom(r ->r.map(...))
```

### Testing Strategy

Two complementary testing approaches:

**Karibu Testing** (extends `KaribuTest`):

- Fast browser-less UI unit tests
- Uses MockVaadin to simulate Vaadin environment
- Set up Spring context with Testcontainers
- Helper method: `login(username, roles)` for authentication in tests
- Use LocatorJ syntax: `_get<Button> { text = "Save" }._click()`

**Playwright with Mopo** (extends `PlaywrightIT`):

- Full E2E tests with real browser
- Runs at random port (`@SpringBootTest(webEnvironment = RANDOM_PORT)`)
- Uses Mopo library to simplify Vaadin component interactions
- Set `launchOptions.headless = false` during development to see the browser

Both test types use `TestcontainersConfiguration` for consistent database state.

### Security Model

The application uses JWT-based stateless authentication:

- JWT tokens are encrypted with HS256 algorithm using a secret from `jwt.auth.secret` property
- Tokens are issued by `VaadinStatelessSecurityConfigurer`
- Users are stored in the database with BCrypt-hashed passwords
- Roles are stored in `user_role` table and mapped to Spring Security authorities with `ROLE_` prefix
- Access control uses Vaadin's `@AnonymousAllowed`, `@PermitAll`, and `@RolesAllowed` annotations

For development, a sample secret is provided in application.properties. Generate a production secret with:

```bash
openssl rand -base64 32
```

## Important Development Notes

### Database Schema Changes

1. Create a new migration file in `src/main/resources/db/migration/` (follow naming: `V###__description.sql`)
2. Run `./mvnw compile` to regenerate jOOQ classes
3. Restart the application

### Error Prone and NullAway

The project uses Error Prone with NullAway for static analysis:

- All code in `ch.martinelli.demo.casemanagement` must be null-safe
- Use `@org.jspecify.annotations.Nullable` for nullable parameters/returns
- Compilation will fail on warnings (`failOnWarning=true`)
- Generated sources are excluded from checks

### Vaadin Components

This project uses Java-based Vaadin Flow (not React/Hilla):

- Components are created in Java (e.g., `new Button("Click me")`)
- Views are annotated with `@Route`, `@PageTitle`, and `@Menu`
- Custom styling goes in `src/main/frontend/themes/`
- The main layout is `MainLayout` with AppLayout, drawer navigation, and language switching

### i18n

Translation files are in `src/main/resources/vaadin-i18n/`:

- `translations.properties` (English)
- `translations_de.properties` (German)

Use `getTranslation("key")` in components. Language switching is in MainLayout footer.

### Debugging

The Spring Boot Maven plugin is configured with debug port 5679:

```bash
./mvnw spring-boot:test-run
```

Then attach debugger to port 5679.
