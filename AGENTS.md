# AI Agent Instructions

This file is the repository-level operating contract for coding agents and automation.

## Repository map

- `candidate-service/`, `job-service/`, and `application-service/` are independent Maven reactor projects. Each has `domain`, `application`, `infra`, `interface`, and `start` modules.
- `shared/contracts/` owns versioned protobuf contracts. `shared/common/` owns shared Java utilities and exception/response types.
- `ai-service/` is a separate Python/FastAPI application.
- `.github/workflows/` contains path-scoped CI for the three Java services.
- `envirnoment/docker-compose-dev.yml` starts the shared local MinIO dependency. Keep the existing spelling when referring to that path.

## Architecture boundaries

For a Java service, preserve the dependency direction:

`interface -> application -> domain`

`infra` implements application output ports; `start` wires modules and runtime configuration. Do not place Spring, MongoDB, HTTP, or gRPC implementation details in `domain`. Add outbound dependencies as application ports first, then implement their adapters in `infra`.

Treat files in `shared/contracts/src/main/proto/` as public APIs. Make protobuf changes additive where possible, retain field numbers, regenerate/compile consumers, and coordinate any breaking change across every service.

## Safe workflow

1. Read the closest `AGENTS.md`, the relevant module POM, configuration, and existing tests before editing.
2. Keep a change within one bounded context unless a contract or explicitly requested cross-service change requires more.
3. Never commit secrets. Add only placeholder names or documented environment variables; use local `.env` files that are ignored by Git.
4. Do not modify generated sources, `target/`, IDE files, or `.DS_Store`.
5. State assumptions when deployment values, external services, or production configuration cannot be verified from the repository.

## Common change situations

### Fixing a bug

- Reproduce the failure with the smallest focused test before changing production code when practical.
- Fix the behavior in the layer that owns it; do not hide domain or application defects in a controller, persistence adapter, or exception handler.
- Add a regression test that fails without the fix. Preserve existing public behavior unless the request explicitly changes it.

### Adding a Java feature

- Start with the owning domain rule or application use case and its input/output ports.
- Add REST or gRPC handling in `interface`, external-system and persistence adapters in `infra`, and wiring or runtime configuration in `start`.
- Reuse the owning service's existing DTO, mapper, validation, and error-response conventions before introducing a new abstraction.

### Changing an API or protobuf contract

- Treat REST payloads and protobuf messages as compatibility-sensitive. Prefer optional or additive changes and do not silently rename or remove fields.
- Never reuse a removed protobuf field number or name; reserve both in the `.proto` file.
- Update all providers and consumers in the same change when compatibility cannot be preserved. Add contract-focused tests and document rollout order and rollback constraints.

### Changing persistence or data shape

- Keep repository interfaces in `application` and MongoDB documents, repositories, and mapping code in `infra`.
- Do not access another service's database directly.
- For a stored-data shape change, describe compatibility with existing records, migration or backfill needs, deployment order, and rollback behavior. Do not run destructive migrations or modify shared data without explicit approval.

### Adding an external or cross-service call

- Define an application output port first and implement the client in `infra`.
- Specify timeout, retry, idempotency, failure mapping, and observability behavior. Do not add unbounded retries or convert dependency failures into successful responses.
- Mock the port in application tests; use focused adapter or contract tests for protocol and mapping behavior.

### Changing configuration, secrets, or dependencies

- Use environment variables for environment-specific values and safe placeholders in committed configuration.
- Document every new variable in the owning configuration and `docs/OPERATIONS.md`, including whether it is required and its safe local default when one exists.
- Before adding or upgrading a dependency, confirm the owning module needs it, prefer dependency management in the reactor POM, and note compatibility or security impact. Do not perform broad upgrades as part of an unrelated change.

### Working on the AI service

- Keep HTTP schemas and route concerns at the FastAPI boundary, business logic in services, and external provider or storage details behind adapters or dedicated clients.
- Pin intentional dependency changes in `ai-service/requirements.txt`; do not commit virtual environments, caches, generated artifacts, or local `.env` files.
- Mock AI providers, MinIO, and network calls in unit tests. Mark tests requiring live services explicitly and do not claim they passed unless those services were actually available.

### Handling tests, failures, and documentation

- Run focused tests while iterating, then the owning service's full validation command before completion. A documentation-only change does not require a full service build.
- Do not weaken, delete, or disable tests, Spotless, JaCoCo, validation, or security controls merely to make a build pass.
- If an unrelated pre-existing failure blocks validation, report the exact command and failure and distinguish it from failures caused by the change.
- Update `README.md`, `ARCHITECTURE.md`, `CONTRIBUTING.md`, or `docs/OPERATIONS.md` when the change affects setup, architecture, contribution workflow, configuration, deployment, or rollback.

### When to stop and ask

- Ask before making a breaking public API change, destructive data operation, security-policy change, or cross-service redesign not explicitly requested.
- Ask when requirements conflict with the architecture boundaries or when production-only values or behavior are required and cannot be verified from repository evidence.
- Do not overwrite unrelated working-tree changes. If they overlap the required edit and cannot be preserved safely, stop and explain the conflict.

## Validation commands

Run from the repository root unless stated otherwise:

```bash
mvn -f shared/pom.xml clean install -DskipTests
mvn -f shared/common/pom.xml clean install -DskipTests
mvn -f candidate-service/pom.xml clean verify
mvn -f job-service/pom.xml clean verify
mvn -f application-service/pom.xml clean verify
```

For a Java formatting-only repair, run `mvn -f <service>/pom.xml spotless:apply`, then re-run `clean verify` for that service. For AI-service work, create an isolated virtual environment and install from `ai-service/requirements.txt`; add focused tests before claiming behavior is covered.

## Definition of done

- Domain, application, adapter, and API changes are aligned.
- Relevant unit/integration/contract tests exist and pass.
- Java changes pass `clean verify` (including Spotless and JaCoCo thresholds).
- Any API, protobuf, configuration, data, security, or operational impact is documented in the pull request and in the appropriate repository document.
