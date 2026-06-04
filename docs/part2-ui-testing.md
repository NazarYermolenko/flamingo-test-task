# Part 2: UI Testing

## Target Website
**URL:** `https://demoqa.com`

## Test Sections

### Option A: Form + Web Tables (Recommended)
- **Form Submission:** [Automation Practice Form](https://demoqa.com/automation-practice-form)
    - Fill registration form.
    - Upload a file.
    - Select a date from the date picker.
    - Choose from dropdowns.
    - Submit and verify success modal.
- **Web Tables:** [Web Tables](https://demoqa.com/webtables)
    - Add a new record.
    - Edit existing record.
    - Delete record.
    - Search functionality.
    - Sorting validation.

### Option B: E-commerce Flow (Alternative)
- **Book Store:** [Books](https://demoqa.com/books)
    - Login (create account or use existing).
    - Search for books.
    - Add/Remove books from collection.
    - Logout.

## UI Test Requirements
- **Tool:** Playwright for Java.
- **Pattern:** Page Object Model (POM).
- **Execution:** Handle dynamic waits properly.
- **Failure Handling:** Take screenshots of failures.

## Minimum Requirements
- At least 2 UI tests with Page Object Model.
