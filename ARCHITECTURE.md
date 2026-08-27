# Architecture

This document describes the architecture visible in the repository. Deployed topology, production values, and external platform behavior remain unknown unless represented by checked-in configuration.

## System context

The repository contains four independently runnable services and two shared Java artifacts.

```text
HTTP clients
  |-- candidate-service ----> candidate MongoDB
  |                           MinIO
  |                           gRPC CandidateService + ResumeService
  |
  |-- job-service ----------> job MongoDB
  |                           gRPC JobService
  |
  |-- application-service --> application MongoDB
  |          |-- gRPC ------> candidate-service
  |          `-- gRPC ------> job-service
  |
  `-- ai-service -----------> MinIO
                              OpenAI API

shared/common     Java cross-cutting types
shared/contracts  protobuf source of truth
```

`candidate-service`, `job-service`, and `application-service` are separate Maven reactors targeting Java 23 and Spring Boot 4.0.6. `ai-service` is a separate Python/FastAPI application.

## Java service structure

Each Java bounded context uses ports and adapters with five modules:

| Module | Owns | Dependency rule |
| --- | --- | --- |
| `*-domain` | aggregates, entities, value objects, domain invariants | no framework or adapter dependency |
| `*-application` | use cases, DTOs, mappers, input/output ports | depends on domain |
| `*-interface` | REST controllers and gRPC server adapters | depends on application and contracts as needed |
| `*-infra` | Mongo repositories, MinIO, gRPC clients, external configuration | implements application output ports |
| `*-start` | Spring Boot entry point and runtime wiring | composes interface and infra |

The intended dependency shape is:

```text
interface ----> application ----> domain
infra --------> application ----> domain
   \              /
    `--- start ---'
```

Spring component scanning in each `StartApplication` connects the layers. Mongo repository scanning is limited to the owning service's infra package.

## Bounded contexts

### Candidate

Owns candidate profiles and resumes. REST controllers expose candidate lifecycle/profile operations and resume upload. Mongo adapters persist candidate and resume documents; MinIO stores files. The service provides candidate-existence and candidate/resume-association checks through gRPC.

### Job

Owns job requirements and lifecycle. REST controllers handle job commands, Mongo adapters persist job documents, and the service provides a job-existence check through gRPC.

### Application

Owns submitted applications. Its create use case checks resume ownership and job existence through application output ports implemented by blocking gRPC clients, then persists an application. It must not read candidate or job databases directly.

### AI

Exposes `/health` and `/api/v1/ai/resumes/extract`. The extraction flow downloads a file from MinIO, parses PDF/DOCX text, and sends extracted text to an OpenAI-backed client for structured candidate data. HTTP concerns live under `presentation`, orchestration under `application`, schemas under `domain`, and provider/storage/parser code under `infrastructure`.

## Contracts and compatibility

- Java REST endpoints are under `/api/v1`.
- `shared/contracts/src/main/proto/{candidate,job,resume}/v1` is the gRPC source of truth.
- Protobuf changes should be additive. Never change the meaning of an existing field, reuse a field number, or remove a field without reserving its name and number.
- A breaking contract change requires coordinated provider/consumer changes, deployment ordering, and rollback constraints.

## Data and security boundaries

- Each Java service owns a separate logical MongoDB configuration.
- Candidate-service and AI-service use MinIO; shared bucket/object ownership conventions are not yet formally specified.
- Resume content and candidate profiles are sensitive personal data. Do not place them in logs, test fixtures copied from production, or AI prompts beyond the minimum required processing path.
- Authentication, authorization, audit policy, retention, encryption policy, and production secret management are not defined in this repository and require explicit design before production use.

## Known gaps

- Automated Java tests currently exist only for candidate domain/application code; job, application, and AI test suites are absent.
- Java CI exists for the three Java services; no AI-service CI is checked in.
- gRPC deadlines, retry policy, and consistent failure translation are not visibly configured on the blocking clients.
- Production deployment manifests, observability backends, SLOs, and rollback automation are not checked in.
- The root compose file supplies MinIO only; MongoDB and the external AI provider must be supplied separately.

Record consequential decisions in `docs/decisions/` and update this file when component ownership, dependency direction, or system integrations change.
