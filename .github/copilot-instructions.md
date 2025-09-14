# Copilot Instructions — Spring Boot 2.7.6 on Java 8

> Use these guardrails when asking Copilot/AI to generate, review, or refactor code in this repository.

---

## 1) Constraints (read first)
- **Platform:** Java 8, Spring Boot **2.7.6** (Maven). **Do not** use `jakarta.*` or bump dependencies/Boot version unless explicitly requested.
- **Injection:** Use **constructor injection only** (no field injection).
- **Logging:** `slf4j` only. **No PII** in logs. Always set MDC keys: `requestId`, `userId`.
- **Exceptions:** Prefer **specific** exception types; avoid broad `Exception` and empty `catch` blocks.
- **Libraries:** No Lombok. Avoid static Mockito unless strongly justified.

---

## 2) Project structure
- Modules: `api`, `service`, `domain`, `batch`, `integration`.
- Database: **Oracle**. JPA: collections are **LAZY** by default. Avoid **N+1** (use fetch-joins, projections, or DTOs).
- DB naming: Prefer **lowercase, unquoted** table/column names for portability. If a module requires UPPERCASE, annotate explicitly.

---

## 3) Build, test, and run
- Build (skip tests): `mvn -q -DskipTests package`
- Run tests: `mvn -q test`
- Run app: `mvn spring-boot:run -Dspring-boot.run.profiles=dev`

---

## 4) Coding standards
- Naming: Classes **PascalCase**; variables **camelCase**; constants **UPPER_SNAKE_CASE**.
- Methods: Keep **small**, reduce cognitive complexity, and add **Javadoc** to public methods.
- Logging details:
  - Use `org.slf4j.Logger`.
  - Do not log credentials, tokens, PAN, or customer data.
  - Ensure `MDC.put("requestId", ...)` and `MDC.put("userId", ...)` are set in request scope (filters/interceptors) and cleared.
- **Team-specific rule:** Any new method added to `com.advaya.UserService` **must declare** `throws com.advaya.UserException`. Make this an explicit Copilot suggestion when generating methods in that class.
---

## 5) Spring & JPA guidance
- Entities & repositories must be clearly annotated.
- Prefer **DTOs** for view models and API responses.
- For read-heavy queries, use **fetch-joins** or projections to avoid N+1.
- Keep transactional boundaries in services; do not open transactions in controllers.

---

## 6) Testing policy (JUnit 5 + Mockito)
- Per **service**: add **1 happy path** + **≥2 edge cases**.
- Use `@ExtendWith(MockitoExtension.class)` with constructor injection for collaborators.
- Avoid static mocking unless there is a compelling reason (document it in the test).

---

## 7) Review checklist (apply to any suggested change)
- ✅ Builds on **Java 8** & **Boot 2.7.6**
- ✅ Unit tests added/updated (**happy + edges**) and pass locally
- ✅ Logging follows policy (**no PII**, MDC set)
- ✅ Minimal diff; **no unnecessary deps** or framework changes

---

## 8) Pull requests
- Keep diffs **minimal** and aligned to **Java 8 / Boot 2.7.6**.
- Include unit tests for new logic.
- If adding MDC usage, update logback config as needed.

---

## 9) Commit message rules
- Format: `<type>(<scope>): <summary>`
- Types: `feat|fix|docs|style|refactor|test|chore`
- Summary: **imperative mood**, ≤ **72 chars**. Include module scope when helpful (e.g., `batch-reports-api`).

**Examples**
- `feat(logging): add MDC to UserService`
- `fix(repo): handle LazyInitializationException with transactional boundary`
- `feat(logging): add MDC (requestId,userId) to controllers`

---

## 10) Prompts for Copilot (copy/paste)
- “Follow our repo constraints: Java 8, Spring Boot 2.7.6, no jakarta, constructor injection, slf4j logging without PII, and MDC (requestId,userId). Generate unit tests (1 happy + 2 edge) for the service.”
- “When adding a method in `com.advaya.UserService`, ensure the method signature **declares** `throws com.advaya.UserException`.”
- “Propose a minimal refactor that reduces cognitive complexity without changing behavior; limit diff size and avoid new dependencies.”
- “When adding a method in `com.advaya.BeneficiaryService`, ensure the method signature **declares** `throws com.advaya.BeneficiaryException`.”

### 1) UserService exception contract
**Rule:** In `com.advaya.UserService` (and subclasses), every public method **must declare** `throws com.advaya.UserException`.  
**Rationale:** Keeps service errors uniform for the controller advice layer.  
**Copilot suggestion:** “When generating methods in `com.advaya.UserService`, add `throws com.advaya.UserException` to the signature and propagate domain errors with this type.”

### 2) BeneficiaryService exception contract
**Rule:** In `com.advaya.BeneficiaryService` (and subclasses), every public method **must declare** `throws com.advaya.BeneficiaryException`.  
**Rationale:** Keeps service errors uniform for the controller advice layer.  
**Copilot suggestion:** “When generating methods in `com.advaya.BeneficiaryService`, add `throws com.advaya.BeneficiaryException` to the signature and propagate domain errors with this type.”