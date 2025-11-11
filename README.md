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

<img width="1536" height="1024" alt="image" src="https://github.com/user-attachments/assets/3dd5036c-baf0-4b3b-ade5-4add0026f2f0" />


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

| # | Evidence Description                    | File Name                                                          | Notes                                                                                                                                     |
|---|-----------------------------------------|--------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------|
| 1 | **JaCoCo Coverage Report (97%)**        | ![jacoco_summary](docs/screenshots/jacoco_summary.png)             | Visual proof of the coverage report generated by JaCoCo (`target/site/jacoco/index.html`), showing a total coverage of 97%.               |
| 2 | **Database Schema (Flyway Migration)**  | ![db_schema](docs/screenshots/db_schema.png)                       | Entity diagram for `City`, `Hotel`, and `Reservation` with referential integrity, generated in PgAdmin.                                   |
| 3 | **Postman — API Collection Overview**   | ![postman collection](docs/screenshots/postman_c.png)              | Overview of all implemented endpoints: cities, hotels, and reservations (GET/POST).                                                       |
| 4 | **Postman — Create City (POST)**        | ![postman create city](docs/screenshots/postmanCC.png)             | Demonstration of a successful city creation (`201 Created`).                                                                              |
| 5 | **Postman — Create Hotel (POST)**       | ![postman create hotel](docs/screenshots/postmanCH.png)            | Creation of a hotel linked to a city. Response validates nested city JSON data.                                                           |
| 6 | **Postman — Create Reservation (POST)** | ![postman create reservation](docs/screenshots/postmanCR.png)      | Valid reservation creation; response includes hotel details and booking dates.                                                            |
| 7 | **Postman — Get Reservations (GET)**    | ![postman get reservation](docs/screenshots/postmanGR.png)         | Fetches all existing reservations with Postman test validation (`200 OK`).                                                                |
| 8 | **Backend Folder Structure (VS Code)**  | ![postman Project structure](docs/screenshots/ProjectStructure.png) | Visual proof of the Java backend directory tree (`src/main`, `api`, `domain`, `repository`, `service`, `test`).                           |
| 9 | **JUnit Test Implementation**           | ![JUnit Tests](docs/screenshots/JunitTests.png)                    | Screenshot of the `ReservationControllerTest` class showing usage of `MockMvc`, `Mockito`, and successful test execution (`@WebMvcTest`). |

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


# BookingMx — Sprint 2 (Jest + Frontend Visualization + 90% Coverage)

## Table of Contents
1. Project Overview
2. Sprint 2 Objectives and Deliverables
3. System Architecture and Folder Structure
4. Jest Environment Setup
5. Graph Visualization Module
6. Unit Tests and Coverage
7. Frontend Interface (Astro)
8. Technical Notes and Reflection
9. Evidence Table
10. Rubric Alignment — Achieving C2 Level
11. Next Steps and Continuous Improvement

---

## 1. Project Overview
**Sprint 2** focuses on the **frontend graph visualization module** of the BookingMx system, implemented in **JavaScript (ESM)** and tested with **Jest**.  
The goal was to validate the functionality, performance, and robustness of the module that displays **nearby cities and their distances** in a visual graph.  
All tests were executed using **Jest**, ensuring **≥ 90% coverage** with a comprehensive suite that includes edge cases, invalid data handling, and error resilience.  
In addition, a **frontend prototype** was developed using **Astro**, inspired by the Trivago interface, demonstrating how users can visualize results and interact with the system.

---

## 2. Sprint 2 Objectives and Deliverables

### 🎯 Objectives
- Develop a modular and testable **JavaScript component** to manage city graph logic.
- Configure and run **Jest** for automated unit testing.
- Guarantee a **minimum of 90% coverage** across lines, functions, and statements.
- Design a **simple, professional frontend** (Astro) similar to Trivago.
- Document all technical decisions, encountered issues, and strategies.

### 📦 Deliverables
| # | Deliverable | Description |
|---|--------------|-------------|
| 1 | `citiesGraph.js` | JavaScript module implementing graph validation, adjacency, and SVG formatting. |
| 2 | `citiesGraph.test.js` | Jest test suite with coverage thresholds ≥ 90%. |
| 3 | `jest.config.cjs` + `babel.config.cjs` | Jest + Babel configuration for ES modules. |
| 4 | `index.astro` | Frontend page with a search bar and SVG city graph visualization. |
| 5 | `TECH_NOTES_SPRINT2.md` | Reflection document with encountered issues and solutions. |
| 6 | Screenshots | Jest results, coverage, frontend views, and notes. |

---

## 3. System Architecture and Folder Structure

```
bookingmx/
├─ backend/ # Spring Boot module (Sprint 1)
└─ web/
   ├─ src/
   │  ├─ lib/graph/
   │  │  ├─ citiesGraph.js # core logic
   │  │  └─ tests/citiesGraph.test.js
   │  ├─ pages/index.astro # frontend UI
   │  └─ styles/main.css
   ├─ jest.config.cjs
   ├─ babel.config.cjs
   ├─ package.json
   ├─ TECH_NOTES_SPRINT2.md
   └─ docs/screenshots/
```

This structure ensures separation between **logic**, **testing**, and **presentation**, facilitating maintenance and scalability.

---

## 4. Jest Environment Setup

**Testing Stack**
- **Jest** for unit testing  
- **babel-jest** for ES module compatibility  
- **jsdom** as testing environment  

**Coverage thresholds** were defined in `jest.config.cjs`:

```js
coverageThreshold: {
  global: { lines: 0.90, statements: 0.90, functions: 0.90, branches: 0.80 }
}
```

**Report generation:**
- Text summary in terminal.  
- HTML report in `/coverage/lcov-report/index.html`.

---

## 5. Graph Visualization Module

**File:** `citiesGraph.js`

Implements:
- `validateData()` — checks duplicates, invalid distances, and missing city references.
- `buildAdjacency()` — creates bidirectional connections for easy lookup.
- `nearbyWithinRadius()` — filters cities within a defined radius.
- `formatGraphForSvg()` — generates x/y coordinates for SVG visualization.

All functions are pure and independent, enabling full coverage testing.

---

## 6. Unit Tests and Coverage

**File:** `citiesGraph.test.js`

### Test Groups
✅ Validation logic (`validateData`)  
✅ Adjacency creation (`buildAdjacency`)  
✅ Distance filtering (`nearbyWithinRadius`)  
✅ SVG output structure (`formatGraphForSvg`)  
⚠️ Edge cases (radius ≤ 0, missing IDs, duplicates)

**Example Test**
```js
test('radius ≤ 0 returns empty array', () => {
  expect(nearbyWithinRadius(1, 0, edges)).toEqual([]);
});
```

**Execution Commands**
```bash
npm run test
npm run test:cov
```

**Achieved Results**
✅ 100% passing tests  
✅ Global coverage > 90%  
✅ No warnings or skipped tests  
✅ Clear error messages for invalid data

---

## 7. Frontend Interface (Astro)

The frontend prototype was built in Astro and inspired by Trivago’s design.

**Features:**
- Responsive search bar with fields for destination, dates, and guests.  
- Partner badges (Booking.com, Expedia, Trip.com, Hotels.com).  
- Dynamic SVG graph showing connected cities with distances.  
- Clean modern CSS layout following minimal UI patterns.

**File:** `src/pages/index.astro`  
Includes an embedded `<script type="module">` using the exported graph functions to render data in SVG.

---

## 8. Technical Notes and Reflection

### Technical Challenges
- Managing coverage thresholds in Jest with ES modules.  
- Designing pure functions with no DOM dependency for easier testing.  
- Handling invalid input and unexpected edge cases.

### Strategies and Solutions
- Modular design: small functions with single responsibilities.  
- Independent test data (mock cities and edges).  
- Strict coverage thresholds enforced in Jest config.  
- Documentation of strategies in `TECH_NOTES_SPRINT2.md`.

### Peer Review (optional)
A short cross-review was conducted with another team member to evaluate test clarity and maintainability. Minor improvements were suggested for naming conventions and input validation messages.

---

## 9. Evidence 

<img width="1024" height="1536" alt="Image" src="https://github.com/user-attachments/assets/9812b73c-db58-437f-a6d4-f02589247036" />

<img width="1536" height="1024" alt="Image" src="https://github.com/user-attachments/assets/c60c784a-6153-4349-8c8b-01478eeca4da" />

---

## 10. Rubric Alignment — Achieving Level C2

| Rubric Criterion | Demonstrated Evidence |
|------------------|------------------------|
| **Knowledge Integration** | Deep understanding of testing, modular design, and input validation. |
| **Technical Competence** | Configured Jest + Babel + ESM successfully, achieving full automation. |
| **Innovation** | Implemented pure functions enabling isolated testing and SVG rendering. |
| **Documentation** | Clear README, inline comments, and technical reflections. |
| **Coverage & Quality** | 90% + coverage, all tests pass, no linting errors. |
| **Professionalism & Collaboration** | Consistent Git commits, peer feedback integrated, reproducible build process. |

✅ **C2 achieved:** Demonstrates autonomy, high-quality documentation, reflective practice, and excellent software engineering rigor.




## 👥 Contributors

| Name | Role | Responsibility |
|------|------|----------------|
| Leonel Campos | Backend Developer | JUnit testing and API logic |
| Raziel Fernandez | Frontend Developer | Jest testing and graph UI |
| Raziel Fernández | Project Coordinator | Documentation, review, and integration |

---

© 2025 **BookingMx Development Team**. All rights reserved.
