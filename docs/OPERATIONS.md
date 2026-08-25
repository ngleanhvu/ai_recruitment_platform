# Operations Guide

## Configuration ownership

| Component | Required configuration visible in source |
| --- | --- |
| Candidate service | active profile, HTTP port, Mongo URI/database, MinIO endpoint/credentials/bucket, gRPC port |
| Job service | active profile, HTTP port, Mongo URI/database, gRPC port |
| Application service | active profile, HTTP port, Mongo URI/database, candidate and job gRPC host/ports |
| AI service | MinIO settings and AI-provider key/model; app name, environment, and port have defaults |

The exact names are defined in each service's `application-dev.yml` or `ai-service/app/config/settings.py`. Supply them through your local shell, local `.env` files, or an approved secret manager—never through committed secrets.

## Local dependency

The repository provides a MinIO compose definition:

```bash
docker compose -f envirnoment/docker-compose-dev.yml up -d
```

This file uses development credentials and must not be treated as a production configuration. MongoDB and external AI-provider configuration are not supplied by the root compose file.

## Runbook minimum for a deployment

Before deployment, record the artifact version, target environment, configuration source, migration/data implications, health check, owner, and rollback version. Validate:

1. Required secrets and network access exist.
2. MongoDB, MinIO, and gRPC dependencies are reachable where applicable.
3. Readiness/health checks pass after rollout.
4. Core smoke paths work without exposing candidate or resume data in logs.
5. A rollback artifact and owner are available.

## Incident first response

1. Identify the affected service, release version, time window, and request/correlation identifier.
2. Check dependency reachability and recent configuration/deployment changes.
3. Mitigate safely: roll back, disable the failing path, or scale only according to an approved procedure.
4. Preserve relevant logs and write a follow-up with cause, customer impact, and preventive action.

## Required next artifacts

Add environment-specific deployment manifests/pipelines, dashboards, alerts, and service-level objectives only after the target platform and production requirements are known. Those choices cannot be safely inferred from this repository.
