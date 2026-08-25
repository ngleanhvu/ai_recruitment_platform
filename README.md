# AI Recruitment Platform

A multi-service recruitment platform with candidate, job, and application workflows, plus an AI service for resume extraction.

## Services

| Service | Runtime | Responsibility |
| --- | --- | --- |
| `candidate-service` | Java / Spring Boot | Candidate profiles, resumes, and MinIO-backed files |
| `job-service` | Java / Spring Boot | Job creation and lifecycle |
| `application-service` | Java / Spring Boot | Applications; validates related candidates, resumes, and jobs through gRPC |
| `ai-service` | Python / FastAPI | Resume extraction API |
| `shared` | Java / Protobuf | Shared utilities and gRPC contracts |

See [ARCHITECTURE.md](ARCHITECTURE.md) for boundaries and integrations, [CONTRIBUTING.md](CONTRIBUTING.md) for local development, and [docs/ENGINEERING_PLAN.md](docs/ENGINEERING_PLAN.md) for the engineering roadmap.

## Quick start

Prerequisites: JDK 23, Maven, Docker Compose, and Python 3. The Java services obtain their active profile and connection settings from environment variables; required variable names are shown in each service's `application-dev.yml`.

Start the shared local dependency:

```bash
docker compose -f envirnoment/docker-compose-dev.yml up -d
```

Build shared libraries, then one Java service:

```bash
mvn -f shared/pom.xml clean install -DskipTests
mvn -f shared/common/pom.xml clean install -DskipTests
mvn -f candidate-service/pom.xml clean verify
```

Do not commit credentials used for local MongoDB, MinIO, or AI-provider access. See [docs/OPERATIONS.md](docs/OPERATIONS.md) before running or deploying a service.
