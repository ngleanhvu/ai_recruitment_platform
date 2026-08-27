# Repository Agent Contract

These instructions apply to the entire repository. Read the nearest nested `AGENTS.md` for component-specific rules, then use `ARCHITECTURE.md` and the relevant file under `docs/` as durable context.

## Read before changing code

1. Inspect `git status` and preserve unrelated worktree changes.
2. Read `ARCHITECTURE.md`, the owning component's `AGENTS.md`, its parent POM or Python requirements, runtime configuration, and nearby tests.
3. Trace the behavior from an inbound adapter through the use case/domain and any outbound port before choosing the edit location.
4. Keep the change inside one bounded context unless the request explicitly requires a shared-contract or cross-service change.

## Repository map

- `candidate-service/`: candidate profiles, resumes, MongoDB persistence, MinIO storage, REST, and gRPC.
- `job-service/`: job lifecycle, MongoDB persistence, REST, and gRPC.
- `application-service/`: job applications, MongoDB persistence, and gRPC clients for candidate/resume/job checks.
- `ai-service/`: FastAPI resume extraction using MinIO and an OpenAI client.
- `shared/contracts/`: versioned protobuf source shared by Java services.
- `shared/common/`: shared Java exceptions, responses, entities, validation, and storage abstractions.
- `docs/`: development, testing, operational, and decision records.
- `envirnoment/docker-compose-dev.yml`: shared local MinIO compose file. The misspelling is an existing path; do not silently rename it.

## Non-negotiable boundaries

- Java dependencies flow inward: `interface` and `infra` depend on `application`; `application` depends on `domain`; `start` performs wiring. Domain code must not import Spring, MongoDB, HTTP, gRPC, MinIO, or persistence types.
- Put business rules in `domain`, orchestration and ports in `application`, inbound protocol mapping in `interface`, external adapters in `infra`, and bootstrap/configuration in `start`.
- A service owns its data. Never query another service's MongoDB directly; use a versioned API or gRPC contract.
- Treat REST payloads and `shared/contracts/src/main/proto/**` as public compatibility surfaces. Prefer additive changes, never reuse protobuf field numbers, and reserve removed names and numbers.
- Keep credentials and candidate/resume PII out of source, fixtures, logs, exceptions, prompts, and documentation. Use synthetic test data and environment variables.
- Do not edit generated protobuf sources, `target/`, `.venv/`, IDE metadata, caches, or `.DS_Store`.

## Change rules

### Behavior changes

- Reproduce a bug with the smallest focused test when practical; add a regression test with the fix.
- Reuse existing DTO, mapper, exception, validation, and port patterns before creating new abstractions.
- Do not weaken tests, coverage, formatting, validation, or security controls to make a check pass.

### Contracts and data

- Update provider and consumer code together when compatibility cannot be preserved.
- For persistence-shape changes, document old-record compatibility, migration/backfill, deployment order, and rollback. Do not run destructive migrations without explicit approval.
- For external calls, define timeout, retry, idempotency, error mapping, and observability behavior. Retries must be bounded.

### Dependencies and configuration

- Add dependencies only to the module that owns the need; prefer versions managed by the reactor POM.
- Pin intentional Python dependency changes in `ai-service/requirements.txt`.
- Document new environment variables in `docs/OPERATIONS.md`. Commit placeholders, never real secrets.

## Validation

Build shared artifacts before a Java service in a clean environment:

```bash
mvn -f shared/pom.xml clean install -DskipTests
mvn -f candidate-service/pom.xml clean verify
mvn -f job-service/pom.xml clean verify
mvn -f application-service/pom.xml clean verify
```

During iteration, run the smallest relevant module/test first. Before handoff, run the owning component's full check from its nested `AGENTS.md`. Documentation-only changes require link/content checks, not a full application build.

If a check cannot run because of missing infrastructure or a pre-existing failure, report the exact command and failure; do not claim it passed.

## Code Review Rules

- Flag violations of the layer and service-ownership boundaries above.
- Flag breaking REST/protobuf changes without a compatibility and rollout plan.
- Flag unbounded network calls, retries without idempotency analysis, swallowed dependency failures, and missing error mapping.
- Flag secrets or real applicant data in any committed artifact.
- Flag behavior changes without focused tests, except when the repository currently lacks the required test harness and the gap is explicitly documented.

## Definition of done

- The implementation is in the owning layer and unrelated worktree changes are untouched.
- Focused tests cover changed behavior and the owning component's validation passes, or blockers are reported precisely.
- Contract, configuration, data, security, deployment, and rollback impacts are documented when applicable.
- `ARCHITECTURE.md` or `docs/` is updated when durable project knowledge changed.
