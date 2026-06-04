# Flamingo Test Task — Automation Suite

API and UI test automation for the Flamingo test assignment. API tests use **JUnit 5**, **REST Assured**, and **AssertJ** against [Restful Booker](https://restful-booker.herokuapp.com) and the Hygraph Countries GraphQL API.

## Prerequisites

- Java 17+
- Maven 3.6+
- Network access to external APIs (`restful-booker.herokuapp.com`, Hygraph GraphQL endpoint)
- Chrome (for UI tests, when added)

## Run tests

```bash
# All tests
mvn test

# API tests only
mvn test -Dtest="com.flamingo.tests.api.**"

# Restful Booker REST tests only
mvn test -Dtest="com.flamingo.tests.api.restfulbooker.**"

# GraphQL (Countries) tests only
mvn test -Dtest="com.flamingo.tests.api.countries.**"

# UI tests only
mvn test -Dtest="com.flamingo.tests.ui.**"

# Form Submission UI test only
mvn test -Dtest="com.flamingo.tests.ui.formsubmission.**"

# Web Tables UI test only
mvn test -Dtest="com.flamingo.tests.ui.webtables.**"
```

Surefire reports: `target/surefire-reports/`  
Allure report (after a test run): `mvn allure:serve`

## Test strategy (API)

| Area | Package | Coverage |
|------|---------|----------|
| Auth | `...restfulbooker.auth` | Login success/failure, token used for protected endpoints |
| Booking (positive) | `...booking.positive` | Full CRUD flow (create → get → update → delete) |
| Booking (unauthorized) | `...booking.negative.BookingUnauthorizedTest` | `PUT` / `DELETE` without token or with invalid token → 403 |
| Booking (validation) | `...booking.negative.BookingNegativeTest` | Invalid payloads and edge cases (see known issues below) |
| GraphQL | `...countries` | Hygraph Countries API positive/negative scenarios |

Controllers live under `com.flamingo.framework.api`; tests under `com.flamingo.tests.api`.

## Known issues — Restful Booker validation

`BookingNegativeTest` asserts **ideal** API behaviour (`400 Bad Request` for invalid input). The live [Restful Booker](https://restful-booker.herokuapp.com) API often does not validate as documented, so **10 of 11 tests in that class currently fail**. This is intentional: the tests document real defects in the third-party API, not bugs in our framework.

When you run `mvn test -Dtest="com.flamingo.tests.api.**"`, expect **15 passing / 10 failing** (as of the last run) unless the upstream API is fixed.

### Failing scenarios (`BookingNegativeTest`)

| Scenario | Expected | Actual (observed) |
|----------|----------|-------------------|
| Create booking with total price `0` | 400 | 200 |
| Create booking with negative total price | 400 | 200 |
| Create booking with empty `firstname` | 400 | 200 |
| Create booking with empty `lastname` | 400 | 200 |
| Create booking with `null` `firstname` | 400 | 500 |
| Create booking with `null` `lastname` | 400 | 500 |
| Create booking without `bookingdates` | 400 | 500 |
| Create booking without `totalprice` | 400 | 500 |
| Create booking without `depositpaid` | 400 | 500 |
| Check-in date after check-out date | 400 | 200 |

### Passing negative scenarios

| Scenario | Expected | Status |
|----------|----------|--------|
| `GET /booking/{id}` for non-existent ID | 404 | Passes |

### Suites that pass reliably

- `AuthTest` — login and invalid credentials
- `BookingPositiveTest` — CRUD with valid data and auth token
- `BookingUnauthorizedTest` — `PUT` / `DELETE` without token or with `invalid-token` → 403
- `CountriesTest` — GraphQL scenarios

## Challenges and approach

1. **Weak upstream validation** — Negative booking tests encode the *correct* contract (400 for bad input). Failures are logged as known API gaps; see TODOs in `BookingNegativeTest.java`.
2. **Auth on protected endpoints** — Update and delete require a `Cookie: token=...` header. Unauthorized access is covered in `BookingUnauthorizedTest` with a fresh booking per case.
3. **Auth login quirk** — Failed login still returns HTTP 200 with a `"Bad credentials"` body; covered in `AuthTest`.

## Future improvements

- Align `BookingNegativeTest` with actual API responses (soft assertions / `@Disabled` with defect links) or split into “contract” vs “observed behaviour” suites so CI can stay green while defects remain documented.
- Add UI tests (Playwright) and CI (GitHub Actions).
- File upstream defects against Restful Booker for validation gaps listed above.

## References

- [Part 1: API testing requirements](docs/part1-api-testing.md)
- [Restful Booker API docs](https://restful-booker.herokuapp.com/apidoc/index.html)
