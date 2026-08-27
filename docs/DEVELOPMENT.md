# Development Guide

## Prerequisites

- JDK 23
- Maven 3.x
- Python 3 with `venv`
- Docker with Compose
- MongoDB instances/databases for the Java services
- MinIO for candidate and AI file flows
- An OpenAI API key for live AI extraction only

## Build Java components

The root is not a Maven aggregator. Install shared artifacts first, then build the reactor you are changing:

```bash
mvn -f shared/pom.xml clean install -DskipTests
mvn -f candidate-service/pom.xml clean verify
```

Replace `candidate-service` with `job-service` or `application-service` as needed. Maven output belongs under `target/` and must not be committed.

Install the owning reactor, then run its start module after required variables are exported:

```bash
mvn -f candidate-service/pom.xml install -DskipTests
mvn -f candidate-service/candidate-service-start/pom.xml spring-boot:run
```

Equivalent start modules exist for the job and application services.

## Run the AI service

Create a local environment without committing it:

```bash
cd ai-service
python -m venv .venv
source .venv/bin/activate
python -m pip install -r requirements.txt
python -m uvicorn app.main:app --reload --port 8004
```

The settings loader reads environment variables case-insensitively and can read `ai-service/.env`. Keep `.env` local and use only approved development credentials.

## Local infrastructure

Start the checked-in MinIO dependency from the repository root:

```bash
docker compose -f envirnoment/docker-compose-dev.yml up -d
```

The compose file is development-only and does not provide MongoDB. See `OPERATIONS.md` for the configuration inventory.

## Working conventions

- Make the smallest coherent change in one bounded context.
- Follow the nearest `AGENTS.md`; nested instructions refine the root contract.
- Prefer focused tests while iterating, then run the full owning-service gate.
- Use `mvn -f <service>/pom.xml spotless:apply` only for intended Java formatting changes, and inspect the diff afterward.
- Do not mix dependency upgrades, refactors, generated artifacts, or formatting churn into an unrelated feature/fix.
