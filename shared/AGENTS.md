# Shared Module Instructions

These instructions refine the repository root contract for `shared/`.

- `contracts` owns protobuf source; generated Java is build output and must not be edited.
- Prefer additive protobuf evolution. Never reuse a field number or silently change field meaning. Reserve removed field names and numbers.
- Compile and test every provider and consumer affected by a contract change.
- `common` is for stable, genuinely cross-service code. Do not move service-specific business concepts into it to avoid a local dependency.
- A shared-common change has a broad blast radius; validate all consuming Java reactors when its public behavior changes.

Validation:

```bash
mvn -f shared/pom.xml clean install
```
