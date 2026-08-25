---
name: codebase-understanding
description: Analyze an existing codebase and explain its architecture, behavior, and safe change scope using repository evidence, before proposing or making changes.
---

# Codebase Understanding

Use this skill when the user needs to understand an existing repository, trace a behavior, locate likely change points, or assess implementation risk. Build an evidence-based model before recommending changes. Analysis alone does not authorize code changes.

## Working principles

- Treat repository contents as the source of truth. Distinguish **Verified** statements, supported directly by code or project files, from **Inference** statements, which explain a likely implication of the verified evidence.
- Cite every technical conclusion with an absolute file path and, where practical, a line number. Use `path:line` notation. If a claim spans several places, cite each relevant location.
- State what has not been verified and why. Do not fill gaps with assumptions.
- Preserve scope: do not edit or format files, install dependencies, run migrations, or execute other state-changing commands unless the user explicitly asks. Do not expose secrets found in configuration, environment files, logs, or command output.

## Discovery workflow

Follow this order, adapting the depth to the user’s question.

1. **Establish repository guidance and shape.** Inspect the top-level directory tree and targeted subtrees. Read applicable `AGENTS.md` files first, then `README*`, `CONTRIBUTING*`, architecture documents, and operational notes. Identify workspace boundaries in monorepos before drawing conclusions across packages.
2. **Identify the stack and execution surface.** Inspect manifests and dependency or build files relevant to each target component: for example `package.json`, lockfiles, `pyproject.toml`, `requirements*.txt`, `go.mod`, `Cargo.toml`, `pom.xml`, `build.gradle*`, `Makefile`, `Dockerfile`, and CI configuration. Determine languages, frameworks, build/test/lint commands, runtime configuration, and executable entry points from their definitions rather than their filenames alone.
3. **Trace the relevant runtime slice.** Start at the application bootstrap, CLI command, worker entry point, or library public API. Then follow routing or command dispatch, business or domain logic, persistence and data models, and external integrations. Trace configuration loading and dependency wiring when they affect the behavior under discussion.
4. **Search deliberately.** Prefer `rg` to locate symbols, endpoint paths, configuration keys, environment-variable names, queue topics, SQL tables, and call sites. Open only the surrounding code needed to establish each relationship. When tracing calls, search both definitions and usages; account for framework conventions, generated code, dependency injection, reflection, and configuration-based routing where applicable.
5. **Validate important behavior with tests.** Locate focused unit, integration, contract, or end-to-end tests for the path being explained. Tests corroborate behavior but do not automatically prove production configuration or deployment behavior. If no relevant test exists, say so.

For a large codebase, read by slice rather than sequentially: begin with the requested use case or the closest entry point, expand outward through the call graph and adjacent configuration, then inspect sibling modules only when they supply a dependency or alternative path. Keep a short evidence trail as you go.

## Explaining findings

Separate facts from interpretation in the response:

- **Verified:** Observable code, configuration, documentation, or test behavior, with citations.
- **Inference:** A bounded conclusion drawn from those facts; name the evidence and any condition that could invalidate it.
- **Unknown / needs verification:** A question that repository evidence cannot answer, such as deployed environment values, live database contents, runtime traffic, generated sources not present locally, or undocumented external-service behavior.

Do not infer architecture solely from directory names, file names, or a framework’s usual conventions. Verify through imports, registrations, invocation sites, configuration, tests, or runtime wiring.

## Output patterns

Use the smallest format that answers the request. These concise patterns are available when helpful.

### Codebase map

```markdown
## Codebase map

- **Verified — Runtime and entry points:** … (`/absolute/path/file:line`)
- **Verified — Main modules:** … (`/absolute/path/file:line`)
- **Verified — Data and integrations:** … (`/absolute/path/file:line`)
- **Verified — Build, test, and local run path:** … (`/absolute/path/file:line`)
- **Inference — Architecture:** …, based on …
- **Unknown:** …
```

### Feature or request-flow explanation

```markdown
## Flow: <request or feature>

1. **Verified:** Entry point receives … (`/absolute/path/file:line`)
2. **Verified:** It dispatches to … (`/absolute/path/file:line`)
3. **Verified:** Business logic reads/writes/calls … (`/absolute/path/file:line`)
4. **Verified:** Tests cover … (`/absolute/path/test:line`)

**Inference:** …
**Not verified:** …
```

### Safe change plan (before editing)

```markdown
## Proposed change plan

- **Likely change points:** … (`/absolute/path/file:line`)
- **Why these locations:** …
- **Compatibility and data risks:** …
- **Tests to add or update:** … (`/absolute/path/test:line`)
- **Open questions before implementation:** …

No files have been changed.
```

### Insufficient-evidence report

```markdown
## Unable to verify

- **Question:** …
- **Evidence checked:** … (`/absolute/path/file:line`)
- **Why it remains unknown:** …
- **What would verify it:** …
```

## Do not

- Do not read the whole repository sequentially when a targeted slice can answer the question.
- Do not conclude the architecture from directory names alone.
- Do not modify code or project state during an analysis-only request, including formatting, dependency installation, migrations, or commands with persistent side effects.
- Do not reveal credentials, tokens, private keys, connection strings, or other secrets; redact them if they are relevant to explaining configuration.

## Before responding

Confirm all of the following:

- Applicable repository instructions and orientation documents were read.
- The relevant entry point or public interface was verified.
- Facts, inferences, and unknowns are explicitly separated.
- Technical claims include absolute paths and available line numbers.
- Important behavior is corroborated by relevant tests, or the absence of evidence is stated.
