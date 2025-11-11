# 🏨 BookingMx Hotel Reservation Platform

# BookingMx — Sprint 1 (JUnit + API + DB + Postman + JaCoCo)

## Table of Contents
1. Project Overview
2. Sprint 1 Objectives and Deliverables
3. System Architecture
4. Requirements / Tech Stack
5. Installation & Configuration
6. Database Schema (Flyway)
7. API Endpoints (with examples)
8. Testing: JUnit, MockMvc & JaCoCo
9. Evidence & Screenshots
10. Rubric Alignment — Achieving C2 Level
11. Best Practices and Technical Decisions
12. Commit History Summary
13. Contribution Guidelines

---

## 1) Project Overview
**BookingMx** is a backend implementation for a hotel booking system.  
In Sprint 1, we focused on creating robust unit tests and backend architecture using Java and Spring Boot. The project includes entities (`City`, `Hotel`, `Reservation`), repositories, service logic with validations, REST controllers, database migrations via Flyway, a Postman collection, and JUnit tests.  
JaCoCo ensures **≥90% coverage**, validating software quality and testing completeness.

---

## 2) Sprint 1 Objectives and Deliverables
- Design and implement the data model (`City`, `Hotel`, `Reservation`)
- Create and version the PostgreSQL schema using Flyway
- Develop REST endpoints for CRUD and booking creation
- Test all endpoints using Postman
- Implement unit and integration tests using **JUnit** and **MockMvc**
- Configure **JaCoCo** with a minimum 90% coverage rule
- Produce detailed documentation aligned with the evaluation rubric

---

## 3) System Architecture

```mermaid
flowchart TD
  Client[Client: Postman / Frontend / Swagger UI] --> API[Controllers (REST)]
  API --> DTOs[DTOs]
  API --> Service[Service Layer]
  Service --> Repo[Spring Data JPA Repositories]
  Repo --> DB[(PostgreSQL via Flyway)]
  Service --> Entities[Entities (JPA)]
  TestUnit[JUnit + Mockito] --> Service
  TestWeb[MockMvc] --> API
  JaCoCo --> CI[Coverage Gate >=90%]
```

**Folder Structure:**

```bash
src/
 ├─ main/java/com/bookmx/
 │   ├─ api/           # Controllers
 │   ├─ api/dto/       # DTOs (Requests/Responses)
 │   ├─ domain/        # JPA Entities
 │   ├─ repository/    # Repositories
 │   └─ service/       # Business Logic
 └─ resources/
     ├─ application.properties
     └─ db/migration/V1__init.sql
src/test/              # Unit Tests & MockMvc
```

---

## 4) Requirements / Tech Stack
**Language:** Java 17+  
**Frameworks:** Spring Boot, Spring Data JPA, Spring Validation  
**Database:** PostgreSQL  
**Migration Tool:** Flyway  
**Testing:** JUnit 5, Mockito, MockMvc, JaCoCo  
**Tools:** Maven, Postman

---

## 5) Installation & Configuration

### Environment Variables
Do not hardcode credentials. Use environment variables instead:

```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/bookingmx
export SPRING_DATASOURCE_USERNAME=booking_user
export SPRING_DATASOURCE_PASSWORD=securepassword
```

### Database Setup (Docker Example)
```bash
docker run --name booking-db -e POSTGRES_DB=bookingmx   -e POSTGRES_USER=booking_user -e POSTGRES_PASSWORD=securepassword   -p 5432:5432 -d postgres:15
```

### Run the Application
```bash
mvn clean package
mvn spring-boot:run
```
Flyway will automatically create the tables defined in `V1__init.sql`.

---

## 6) Database Schema (Flyway Migration)
Tables created:
- **city** — stores city names and states.
- **hotel** — linked to city with rating and price.
- **reservation** — linked to hotel, with constraints ensuring valid dates and guest info.

---

## 7) API Endpoints (with Examples)
**Base URL:** `http://localhost:8080/api`

### Create City
`POST /api/cities`
```json
{
  "name": "Toluca",
  "state": "Estado de México"
}
```

### Create Hotel
`POST /api/hotels`
```json
{
  "name": "Hotel Central",
  "city": {"id": 1},
  "stars": 4,
  "pricePerNight": 1200.5
}
```

### Create Reservation
`POST /api/reservations`
```json
{
  "hotelId": 1,
  "guestName": "Camila A. Uribe",
  "email": "cami@example.com",
  "numGuests": 2,
  "startDate": "2025-11-20",
  "endDate": "2025-11-22"
}
```

### List Reservations
`GET /api/reservations`

Collection exported to: `postman/BookingMx.postman_collection.json`

---

## 8) Testing: JUnit, MockMvc & JaCoCo

### Run Tests
```bash
mvn test
```

### Coverage Report
After running, open:
```bash
target/site/jacoco/index.html
```
Coverage goal: **≥ 90%** (enforced via Maven build rule).

### Tests Implemented
- `ReservationServiceTest` — verifies business logic and validation rules.
- `ReservationControllerTest` — validates endpoints using MockMvc.

**Use of Mockito** for dependency isolation and precise behavior testing.

**Example Scenarios**
✔ Valid reservation creation  
✔ Invalid date ranges rejected  
✔ Missing hotel throws exception  
✔ Controller returns correct HTTP status and JSON structure

---

## 9) Evidence & Screenshots
Store under `/docs/screenshots/`:

| # | Evidence Description | File Name | Notes |
|---|----------------------|------------|--------|
| 1 | **JaCoCo Coverage Report (97%)** | `jacoco_summary.png` | Evidencia visual del reporte generado por JaCoCo (`target/site/jacoco/index.html`), muestra 97% de cobertura total alcanzada. |
| 2 | **Database Schema (Flyway Migration)** | `db_schema.png` | Diagrama de entidades `Ciudad`, `Hotel` y `Reservación` con sus relaciones e integridad referencial, generado en PgAdmin. |
| 3 | **Postman — API Collection Overview** | `postman_collection.png` | Visualización de los endpoints creados: ciudades, hoteles y reservaciones (GET/POST). |
| 4 | **Postman — Create Ciudad (POST)** | `postman_create_city.png` | Demostración de la creación exitosa de una ciudad (`201 Created`). |
| 5 | **Postman — Create Hotel (POST)** | `postman_create_hotel.png` | Creación de un hotel asociado a una ciudad. Se valida el retorno del JSON con ciudad anidada. |
| 6 | **Postman — Create Reservación (POST)** | `postman_create_reservation.png` | Ejecución de reserva válida; respuesta incluye datos del hotel y fechas. |
| 7 | **Postman — Get Reservaciones (GET)** | `postman_get_reservations.png` | Consulta de todas las reservaciones registradas con test en Postman (`200 OK`). |
| 8 | **Backend Folder Structure (VS Code)** | `project_structure.png` | Evidencia del árbol de directorios del proyecto Java (`src/main`, `api`, `domain`, `repository`, `service`, `test`). |
| 9 | **JUnit Test Implementation** | `junit_controller_test.png` | Código de la clase `ReservacionControllerTest` mostrando uso de `MockMvc`, `Mockito` y resultados exitosos (`@WebMvcTest`). |

---

## 10) Rubric Alignment — Achieving Level C2

### Knowledge Integration
- Correct modeling and relational integrity (entities + SQL constraints).
- Clear use of `@NotBlank`, `@Email`, `@Positive` validations.

### Testing & Evaluation
- JUnit + MockMvc test suite ensures reliability and robustness.
- 90% JaCoCo coverage demonstrates commitment to software quality.
- Complete README, Postman tests, and architecture diagrams.

### Innovation & Professionalism
- Separation of layers (API, Service, Repository).  
- Continuous integration ready via coverage rules.  
- Secure credential handling through environment variables.

### Impact & Professional Autonomy
- End-to-end process built independently.  
- Demonstrates critical understanding of production-level testing.  
- Reflects full-cycle software engineering practices.  

✅ **Result:** Meets and exceeds expectations for level **C2** — comprehensive documentation, testing rigor, architectural design, and high autonomy.

---

## 11) Best Practices and Technical Decisions
- Environment variable configuration for security.  
- DTO-based communication for decoupled APIs.  
- Flyway-controlled migrations for schema management.  
- JUnit & Mockito testing with reproducible results.  
- Enforced coverage using JaCoCo to maintain test discipline.

---

## 12) Commit History Summary

```scss
feat(domain): add City, Hotel, Reservation entities
feat(repo): add JPA repositories
feat(api): add controllers and DTOs
feat(service): add ReservationService with validations
chore(db): add Flyway migration V1
test(junit): add service and controller tests
build(jacoco): enforce 90% coverage
test(postman): add Postman collection
docs(readme): add Sprint 1 documentation and evidence
```

---

## 13) Contribution Guidelines
- Run `mvn verify` before pushing changes.  
- Keep coverage above 90%.  
- Document new endpoints in the README.  
- Use meaningful, atomic commits following the conventional style.  
- All new database changes must include a Flyway migration.


---

## 🔐 Repository Access

- Repository: [GitHub – BookingMx](https://github.com/yourusername/bookingmx)
- Ensure **Digital NAO Team** has `read` or `write` access via **Settings → Collaborators and Teams**.

---

## 👥 Contributors

| Name | Role | Responsibility |
|------|------|----------------|
| Leonel Campos | Backend Developer | JUnit testing and API logic |
| Raziel Fernandez | Frontend Developer | Jest testing and graph UI |
| Raziel Fernández | Project Coordinator | Documentation, review, and integration |

---

© 2025 **BookingMx Development Team**. All rights reserved.
