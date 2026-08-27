# Candidate Service Instructions

These instructions refine the repository root contract for `candidate-service/`.

- Candidate-service owns candidate profiles, resumes, their Mongo documents, and MinIO-backed candidate files.
- Put invariants and state transitions in `candidate-service-domain`; orchestration and repository/storage ports in `candidate-service-application`; REST/gRPC inbound mapping in `candidate-service-interface`; Mongo/MinIO implementations in `candidate-service-infra`; wiring in `candidate-service-start`.
- Keep file validation and object-key handling at the owning domain/application boundary. Never trust a client filename as a storage key without the existing validation/sanitization path.
- Changes to candidate/resume gRPC behavior must update `shared/contracts` and all consumers, especially application-service.
- Use synthetic candidate and resume data in tests and logs.

Focused validation:

```bash
mvn -f shared/pom.xml install -DskipTests
mvn -f candidate-service/pom.xml -pl candidate-service-domain test
mvn -f candidate-service/pom.xml -pl candidate-service-application -am test
```

Before handoff:

```bash
mvn -f candidate-service/pom.xml clean verify
```
