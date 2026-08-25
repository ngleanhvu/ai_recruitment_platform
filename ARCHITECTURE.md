# Architecture

## Verified current design

This repository contains independently built Maven reactors for candidate, job, and application services, plus a separate FastAPI AI service. Each Java service has `domain`, `application`, `infra`, `interface`, and `start` modules (`candidate-service/pom.xml`, `job-service/pom.xml`, and `application-service/pom.xml`).

The Java boot applications component-scan those layers and enable service-specific Mongo repositories (`candidate-service/candidate-service-start/src/main/java/com/ngleanhvu/candidate/start/StartApplication.java`, `job-service/job-service-start/src/main/java/com/ngleanhvu/job/start/StartApplication.java`, and `application-service/application-service-start/src/main/java/com/ngleanhvu/application/start/StartApplication.java`).

`shared/contracts` defines candidate, resume, and job protobuf APIs. Candidate and job expose gRPC services; application-service has gRPC clients for candidate, resume, and job validation. Candidate-service also configures MinIO for file storage. The AI service exposes FastAPI routes and a health endpoint.

```text
HTTP clients
  |--> candidate-service --> MongoDB, MinIO
  |--> job-service -------> MongoDB
  |--> application-service -> MongoDB
                               |-- gRPC --> candidate-service
                               |-- gRPC --> job-service
  |--> ai-service --------> AI provider + MinIO (configured)

shared/common: common Java utilities and API errors
shared/contracts: protobuf source shared by Java services
```

## Layering rule

The Java services follow a ports-and-adapters style:

| Layer | Owns | May depend on |
| --- | --- | --- |
| `domain` | entities, value objects, domain rules | domain code only |
| `application` | use cases, input/output ports, DTOs | domain |
| `interface` | REST and gRPC inbound adapters | application |
| `infra` | Mongo, MinIO, and gRPC outbound adapters | application ports and external libraries |
| `start` | application bootstrap and configuration | all service modules |

## Integration contracts

- REST controllers are versioned under `/api/v1` in the Java services.
- Protobuf source is the source of truth for gRPC communication. Preserve existing field numbers and prefer additive changes.
- Environment-specific Java configuration is externalized through variables in each `application-dev.yml`. The AI service reads settings from environment variables (and a local `.env` file).

## Important constraints and gaps

- The repository has Java CI workflows for candidate, job, and application service changes, but no AI-service CI workflow.
- No repository tests were present at the time this document was added; the Maven projects nevertheless enforce JaCoCo coverage checks during `verify`.
- Dockerfiles should be verified before use: their copy/build paths do not visibly match the multi-module service layout. This is a deployment readiness item, not an assertion that an image currently fails.
- Production topology, authentication/authorization, observability backend, and actual deployed database settings are not represented here and must be verified with the deployment owner.

## Change guidance

1. Keep business rules in the owning service's domain/application modules.
2. Introduce an application output port before adding an external integration.
3. For a new cross-service call, version its protobuf API, add provider and consumer tests, and define timeout/error behavior.
4. Do not make another service's database a dependency; communicate through its APIs/contracts.
