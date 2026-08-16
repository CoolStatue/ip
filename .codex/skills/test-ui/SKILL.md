---
name: test-ui
description: Run Duke console UI regression tests from a project test plan, compare each command sequence with its expected output, and display the complete console test session. Use when asked to test, verify, or regression-test Duke's command-line interface or its command/output behavior.
---

# Test UI

Use `test/ui-test-plan.md` as the source of truth for all UI tests. Each test case must provide an aim, inputs, and expected output in the required format below.

````markdown
### TC-001: Short descriptive title
**Aim:** State the user-visible behavior being tested.

**Input:**
```text
todo borrow book
bye
```

**Expected output:**
```text
...exact program output, including the greeting...
```
````

Keep the expected output exact apart from line-ending differences. Include `bye` as the final input of every case, so each test run terminates cleanly.

## Run tests

1. Read `test/ui-test-plan.md`. Add or update cases when the requested behavior is not covered.
2. Resolve Python. Prefer `python`; on Windows, locate an installed `python.exe` and use PowerShell's call operator (`&`) if it is not on PATH.
3. From the repository root, run:

   ```bash
   python .codex/skills/test-ui/scripts/run_ui_tests.py
   ```

   The runner verifies Java 25, compiles all files under `src/main/java`, then runs every test case in a separate process.
4. Show the runner's console input/output record in the response. If a test fails, stop immediately; report its expected and actual output, and do not run later cases.
5. Never change expected output merely to make a failing test pass. Explain the mismatch and fix code only when the user has asked for a fix.

## Resource

`scripts/run_ui_tests.py` is a standard-library-only runner. It compares output exactly after normalizing Windows and Unix line endings and one final trailing newline.
