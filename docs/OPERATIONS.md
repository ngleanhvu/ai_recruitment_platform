# Operations Guide

This is a source-level operations baseline, not a production runbook. Production topology and secret-management choices are not present in the repository.

## Configuration inventory

| Component | Environment variables visible in source |
| --- | --- |
| Candidate | `CANDIDATE_ACTIVE_PROFILE`, `CANDIDATE_SERVER_PORT`, `CANDIDATE_MONGO_URI`, `CANDIDATE_MONGO_DATABASE`, `MINIO_ENDPOINT`, `MINIO_ACCESS_KEY`, `MINIO_SECRET_KEY`, `MINIO_BUCKET`, `GRPC_CANDIDATE_SERVER_PORT` |
| Job | `JOB_ACTIVE_PROFILE`, `JOB_SERVER_PORT`, `JOB_MONGO_URI`, `JOB_MONGO_DATABASE`, `GRPC_JOB_SERVER_PORT` |
| Application | `APPLICATION_ACTIVE_PROFILE`, `APPLICATION_SERVER_PORT`, `APPLICATION_MONGO_URI`, `APPLICATION_MONGO_DATABASE`, `CANDIDATE_GRPC_HOST`, `CANDIDATE_GRPC_PORT`, `JOB_GRPC_HOST`, `JOB_GRPC_PORT` |
| AI | `APP_NAME`, `APP_ENV`, `APP_PORT`, `MAX_FILE_SIZE_MB`, `MINIO_ENDPOINT`, `MINIO_ACCESS_KEY`, `MINIO_SECRET_KEY`, `MINIO_BUCKET`, `MINIO_SECURE`, `OPENAI_API_KEY`, `OPENAI_MODEL` |

Java names come from each start module's `application.yml` and `application-dev.yml`; Python names are derived by Pydantic Settings from `ai-service/app/config/settings.py`. Store real values in local environment files or an approved secret manager, never in Git.

## Local dependencies

```bash
docker compose -f envirnoment/docker-compose-dev.yml up -d
```

This starts development MinIO only. Supply MongoDB separately. Do not reuse compose credentials in a shared or production environment.

## Deployment checklist

Before deploying, record:

1. artifact/image version, target environment, and owner;
2. configuration and secret source;
3. API/protobuf and stored-data compatibility;
4. dependency reachability and timeout behavior;
5. health/readiness and a synthetic smoke path;
6. observability queries/dashboards and rollback version;
7. migration, rollout order, and rollback constraints.

Do not log resume text, applicant profiles, credentials, tokens, or raw AI prompts/responses containing PII.

## Incident first response

1. Identify the affected service, release, time window, and request/correlation identifiers when available.
2. Check recent deployments/configuration and MongoDB, MinIO, gRPC, or OpenAI reachability as applicable.
3. Reduce impact with the safest approved action: rollback, traffic control, or feature disablement.
4. Preserve relevant evidence without copying secrets or candidate data into tickets/chat.
5. Document impact, cause, recovery, and a preventive follow-up.

## Rollback constraints

- Code-only rollback is safe only when contracts and stored data remain backward compatible.
- Protobuf or REST breaks require coordinated consumer/provider rollback.
- Data-shape changes require a tested backward path or explicit forward-fix plan.
- AI model changes can alter extraction output without a schema change; validate representative synthetic resumes before rollout.
