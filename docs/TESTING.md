# Testing and Validation

## Current baseline

Candidate-service has domain and application unit tests. No committed tests currently cover job-service, application-service, or ai-service. Treat those as gaps to close when changing their behavior; never describe an absent suite as passing.

## Test placement

| Change | Minimum evidence |
| --- | --- |
| domain invariant/value object | unit test in the owning `*-domain` module |
| application use case/mapper | unit test in `*-application`, mocking output ports |
| REST or gRPC mapping | focused adapter test in `*-interface` |
| Mongo/MinIO/gRPC client | adapter or integration test in `*-infra` |
| protobuf contract | provider/consumer mapping or compatibility test |
| FastAPI/service logic | Python unit/API test with MinIO and OpenAI calls mocked |

Use synthetic applicant and resume data. Tests must not depend on production credentials or live customer data.

## Java commands

Install shared artifacts once in a clean local Maven repository:

```bash
mvn -f shared/pom.xml clean install -DskipTests
```

Focused candidate examples:

```bash
mvn -f candidate-service/pom.xml -pl candidate-service-domain test
mvn -f candidate-service/pom.xml -pl candidate-service-application -am test
```

Full gates:

```bash
mvn -f candidate-service/pom.xml clean verify
mvn -f job-service/pom.xml clean verify
mvn -f application-service/pom.xml clean verify
```

`verify` includes Spotless and configured JaCoCo checks. Do not lower the configured thresholds merely to pass a change.

## AI-service expectations

The current requirements do not include a Python test runner. When adding the first tests, choose and pin the minimum test dependencies, document the command here, and add the same command to CI. Mock MinIO and OpenAI by default; mark live integration tests explicitly and require opt-in credentials.

## Reporting results

For every handoff, state the exact commands run and their results. If a command was not run or infrastructure was unavailable, say so. Distinguish failures caused by the change from pre-existing failures.
