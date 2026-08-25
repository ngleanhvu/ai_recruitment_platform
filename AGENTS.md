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
