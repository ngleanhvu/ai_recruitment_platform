# Job Service Instructions

These instructions refine the repository root contract for `job-service/`.

- Job-service owns job aggregates, requirements, lifecycle transitions, and job persistence.
- Put lifecycle rules in `job-service-domain`; commands/ports/mapping in `job-service-application`; REST/gRPC adapters in `job-service-interface`; Mongo implementation in `job-service-infra`; wiring in `job-service-start`.
- Preserve valid state transitions for publish, close, and expire operations. Reject invalid transitions in the domain/application layer, not only in controllers.
- Changes to job-existence gRPC behavior must update `shared/contracts` and application-service together.
- There are currently no committed job-service tests. Any behavior change should establish focused coverage in the owning module.

Before handoff:

```bash
mvn -f shared/pom.xml install -DskipTests
mvn -f job-service/pom.xml clean verify
```
