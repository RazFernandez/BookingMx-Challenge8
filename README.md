# 🏨 BookingMx Hotel Reservation Platform

## 📖 General Project Description

**BookingMx** is a hotel reservation platform designed to simplify the booking process for users and enhance hotel management capabilities.  
This project currently focuses on two main modules:

1. **Reservations Module (Java – Backend):**  
   Allows users to create, edit, and cancel hotel reservations through a REST API built in Java.  
   Includes validation for room availability, customer data, and reservation status management.

2. **City Graph Visualization (JavaScript – Frontend):**  
   Displays a dynamic graph showing nearby cities relative to the customer’s selected destination.  
   It visualizes distances between cities and supports interactivity for better route and location understanding.

---

## ⚙️ Installation Instructions

### 🧩 Prerequisites
- **Java 17+**
- **Maven 3.8+**
- **Node.js 18+**
- **npm 9+** or **yarn**
- **Git**

---

### 🖥️ Backend Setup (Java – Reservations Module)
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/bookingmx.git
   cd bookingmx/backend
   ```
2. Install dependencies and build the project:
   ```bash
   mvn clean install
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. The API will be available at:  
   `http://localhost:8080/api/reservations`

---

### 🌐 Frontend Setup (JavaScript – Graph Visualization)
1. Move to the frontend folder:
   ```bash
   cd ../frontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Run the development server:
   ```bash
   npm start
   ```
4. Open your browser at:  
   `http://localhost:3000`

---

## 🧪 Unit Testing

### 🔹 JUnit Tests – Reservations Module (Java)
#### Framework:
- **JUnit 5**
- **JaCoCo** for code coverage

#### Test Coverage:
Tests focus on:
- ✅ Creating a reservation  
- ✏️ Editing a reservation  
- ❌ Canceling a reservation  
- ⚠️ Handling invalid inputs and exceptions  

#### Running Tests:
```bash
mvn test
```

#### Coverage Report:
After running the tests, open:
```
/target/site/jacoco/index.html
```

#### Example Test (Java):
```java
@Test
void shouldCreateReservationSuccessfully() {
    Reservation reservation = new Reservation("John Doe", "Hotel Central", LocalDate.now(), 3);
    Reservation created = reservationService.createReservation(reservation);
    assertNotNull(created.getId());
    assertEquals("John Doe", created.getCustomerName());
}
```

**Expected Output (JUnit Console):**
```
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

**Coverage Goal:** ≥ 90%

---

### 🔹 Jest Tests – City Graph Visualization (JavaScript)
#### Framework:
- **Jest**
- **Testing Library (optional, for DOM testing)**

#### Test Coverage:
Covers:
- ✅ Rendering graph with city nodes  
- ✅ Displaying distances correctly  
- ⚠️ Handling empty or malformed data gracefully  
- 🔁 Dynamic updates when destination changes  

#### Running Tests:
```bash
npm test
```

#### Generating Coverage Report:
```bash
npm run test -- --coverage
```

#### Example Test (JavaScript):
```javascript
import { renderGraph } from '../graph';

test('renders cities and distances correctly', () => {
  const mockData = [
    { name: 'Mexico City', distance: 0 },
    { name: 'Puebla', distance: 130 }
  ];
  const result = renderGraph(mockData);
  expect(result.nodes.length).toBe(2);
  expect(result.edges[0].distance).toBe(130);
});
```

**Expected Output (Jest Console):**
```
PASS  tests/graph.test.js
✓ renders cities and distances correctly (15 ms)

----------------|---------|----------|---------|---------|
File            | % Stmts | % Branch | % Funcs | % Lines |
----------------|---------|----------|---------|---------|
All files       |   95.12 |    91.33 |   93.75 |   94.88 |
----------------|---------|----------|---------|---------|
```

---

## 🧾 Test Documentation

| Sprint | Module | Framework | Coverage | Notes |
|--------|----------|------------|-----------|--------|
| 1 | Reservations | JUnit + JaCoCo | 92% | Minor bug fixed in editReservation() |
| 2 | City Graph | Jest | 94% | Added edge-case tests for empty data |

**Technical Issues Found:**
- *Reservation overlapping logic not initially handled.*  
  → Fixed by adding date validation in `ReservationService.validateAvailability()`.
- *Graph module crashed with null data.*  
  → Added null-check and fallback rendering in `renderGraph()`.

---

## 📚 Code Documentation Standards

### Java (Backend)
All classes and methods are documented using **Javadoc** format:

```java
/**
 * Creates a new reservation.
 * @param reservation The reservation details provided by the user.
 * @return The saved reservation entity.
 * @throws InvalidDateException if check-in date is invalid.
 */
public Reservation createReservation(Reservation reservation) { ... }
```

### JavaScript (Frontend)
Functions are documented with **JSDoc** format:

```javascript
/**
 * Renders a city graph based on provided data.
 * @param {Array} cities - List of cities with distances.
 * @returns {Object} Graph object with nodes and edges.
 */
function renderGraph(cities) { ... }
```

---

## 🧩 System Diagrams (in `/docs/diagrams.pdf`)

The **PDF file** includes:
- 🏗️ **System Architecture Diagram** – Overview of backend–frontend interaction  
- 🧱 **Class Diagram (Java)** – Reservation module structure and relationships  
- 🔄 **Flow Diagram (Graph Visualization)** – Data rendering logic and update flow

---

## 🔐 Repository Access

- Repository: [GitHub – BookingMx](https://github.com/yourusername/bookingmx)
- Ensure **Digital NAO Team** has `read` or `write` access via **Settings → Collaborators and Teams**.

---

## 👥 Contributors

| Name | Role | Responsibility |
|------|------|----------------|
| Teresa González | Backend Developer | JUnit testing and API logic |
| Karen Martínez | Frontend Developer | Jest testing and graph UI |
| Miguel Fernández | Project Coordinator | Documentation, review, and integration |

---

## 🏁 Summary

This repository includes:
- Full backend (Java) and frontend (JavaScript) code
- Unit tests with **JUnit** and **Jest**
- Coverage reports (>90%)
- Complete documentation and diagrams
- Access and collaboration setup for review and deployment

---

© 2025 **BookingMx Development Team**. All rights reserved.
