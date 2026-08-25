# Engineering Plan

This plan turns the current service implementation into a maintainable delivery system. It is ordered by risk reduction, not by feature priority.

## Phase 1 — Establish the delivery baseline

- Adopt the repository-level [AGENTS.md](../AGENTS.md), [ARCHITECTURE.md](../ARCHITECTURE.md), and [CONTRIBUTING.md](../CONTRIBUTING.md).
- Add a root `.gitignore` for build output, IDE files, local environment files, and macOS metadata.
- Add `README` links and an environment-variable template with no secrets.
- Verify/fix container build definitions against each multi-module Maven reactor.

**Exit criteria:** a new contributor can identify service ownership, start local dependencies, build a service, and avoid committing local secrets or artifacts.

## Phase 2 — Make quality gates meaningful

- Add unit tests for domain/application behavior and integration tests for Mongo/MinIO adapters.
- Add provider/consumer tests for protobuf APIs before evolving them.
- Keep the current Java Spotless and JaCoCo gates; set baselines only after real tests exist.
- Add an AI-service CI workflow for dependency installation, linting, tests, and security checks.

**Exit criteria:** every changed business behavior has automated coverage and every service has a path-scoped CI check.

## Phase 3 — Container and deployment readiness

- Build immutable, versioned container images in CI; include SBOM and vulnerability scanning.
- Add health/readiness endpoints for every service and use them in deployment checks.
- Select an environment configuration/secrets mechanism and document required variables per service.
- Add staged deployment with smoke tests and a documented rollback procedure.

**Exit criteria:** a build is reproducible, deployable to a non-production environment, observable during rollout, and reversible.

## Phase 4 — Reliability and security

- Define gRPC timeouts, retry policy, error mapping, and compatibility policy.
- Add structured logs, correlation IDs, metrics, traces, and dashboards/alerts.
- Define authentication, authorization, data retention, audit logging, and PII handling for candidate/resume data.
- Exercise failure scenarios: unavailable MongoDB, MinIO, gRPC provider, and AI provider.

**Exit criteria:** service ownership can detect, diagnose, mitigate, and review common production failures.

## Phase 5 — Continuous improvement

- Maintain architecture decision records for consequential choices.
- Track delivery metrics, test reliability, dependency updates, and security remediation.
- Review this plan quarterly and promote recurring operational knowledge into runbooks.
