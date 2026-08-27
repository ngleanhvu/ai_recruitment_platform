# Application Service Instructions

These instructions refine the repository root contract for `application-service/`.

- Application-service owns applications and application persistence; it does not own candidate, resume, or job truth.
- Cross-service checks belong behind application output ports and gRPC implementations in infra. Never bypass those ports with direct database access.
- Define explicit gRPC deadlines and map unavailable/timeout/not-found failures deliberately when changing client calls. Do not add unbounded retries.
- Consider duplicate submission/idempotency and partial-failure behavior before changing application creation.
- There are currently no committed application-service tests. Mock `CandidateGateway`, `ResumeGateway`, `JobGateway`, and `ApplicationRepository` in application-layer tests; keep live gRPC/Mongo concerns in adapter tests.

Before handoff:

```bash
mvn -f shared/pom.xml install -DskipTests
mvn -f application-service/pom.xml clean verify
```
