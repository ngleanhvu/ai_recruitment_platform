# Contributing

## Before opening a pull request

1. Read [AGENTS.md](AGENTS.md) and [ARCHITECTURE.md](ARCHITECTURE.md).
2. Keep the pull request focused on one service or one deliberate cross-service contract change.
3. Add or update tests for changed behavior. There are currently no committed tests, so new behavior should establish focused coverage.
4. Do not add secrets, generated files, build output, or editor metadata.

## Java validation

The Java service reactors use JDK 23, Spotless, and JaCoCo. CI runs the equivalent of:

```bash
mvn -f shared/pom.xml clean install -DskipTests
mvn -f shared/common/pom.xml clean install -DskipTests
mvn -f <service>/pom.xml clean verify
```

Run `mvn -f <service>/pom.xml spotless:check` before requesting review. `clean verify` also enforces the configured line and branch coverage thresholds.

## Contracts and configuration

- Change `shared/contracts` when a gRPC API changes; preserve protobuf compatibility.
- Document every new environment variable in the owning service's configuration and in [docs/OPERATIONS.md](docs/OPERATIONS.md).
- Keep credentials out of YAML, source code, commit history, and pull-request text.

## Pull-request checklist

- [ ] Scope and motivation are clear.
- [ ] Layer boundaries are preserved.
- [ ] Tests and verification commands are included.
- [ ] Contract/configuration/data changes are called out.
- [ ] Operational and rollback impact is stated when relevant.
