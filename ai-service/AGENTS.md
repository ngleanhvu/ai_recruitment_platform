# AI Service Instructions

These instructions refine the repository root contract for `ai-service/`.

- Keep FastAPI routes and HTTP errors in `app/presentation`, extraction orchestration in `app/application`, validated schemas in `app/domain`, and MinIO/parser/OpenAI details in `app/infrastructure`.
- Inject or otherwise isolate external clients when changing extraction logic so MinIO and OpenAI can be mocked in tests.
- Treat resume text and extracted candidate data as sensitive. Do not log full inputs/outputs, place real resumes in fixtures, or expose provider errors directly to clients.
- Validate file size/type and use bounded provider timeouts. Map invalid input separately from storage, parser, rate-limit, and provider failures.
- Preserve structured schema validation at the provider boundary. A model/prompt change is a behavior change and needs representative synthetic fixtures.
- Pin dependency changes in `requirements.txt`; never commit `.env`, `.venv`, bytecode, or caches.
- No Python test runner or suite is currently committed. Add and document focused tests with the first behavior change; mock network/storage calls by default.

Local smoke run (requires configured development dependencies):

```bash
cd ai-service
python -m uvicorn app.main:app --reload --port 8004
```
