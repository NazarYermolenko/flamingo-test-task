# Part 1: API Testing

## RESTful Booker API
**Base URL:** `https://restful-booker.herokuapp.com`
**Documentation:** [Restful Booker API Doc](https://restful-booker.herokuapp.com/apidoc/index.html)

### Required REST Scenarios (REST Assured + JUnit 5)
- **Authentication:**
    - `POST /auth`
    - Body: `{"username": "admin", "password": "password123"}`
    - Goal: Get auth token for subsequent requests.
- **CRUD Operations:**
    - Create a new booking (`POST /booking`)
    - Retrieve the booking by ID (`GET /booking/{id}`)
    - Update the booking (`PUT /booking/{id}`)
    - Delete the booking (`DELETE /booking/{id}`)

## GraphQL Testing
**Documentation:** [GraphQL Playground](https://hygraph.com/graphql-playground)

### Positive Scenarios
- Query a list with pagination/limit (e.g., `launchesPast(limit, offset)`).
- Query a single entity by ID (e.g., `launch(id: ...)`, `rocket(id: ...)`).
- Use GraphQL variables (not string interpolation).
- Use a fragment or nested fields across types (e.g., `launch` -> `rocket` -> `mission`).

### Negative Scenarios
- **Invalid ID:** Assert response shape (usually HTTP 200 with `data: null` or `errors` array).
- **Malformed query:** Assert `errors[].message` and absence of `data`.
- **Non-existent field:** Assert validation error.

## Minimum Requirements
- At least 3 API tests covering CRUD operations.
- At least 5 API tests covering GraphQL.
