#!/usr/bin/env python3
"""Compile Duke and compare each planned console session with expected output."""

from __future__ import annotations

import re
import subprocess
import sys
from dataclasses import dataclass
from pathlib import Path


PLAN_PATH = Path("test/ui-test-plan.md")
SOURCE_DIRECTORY = Path("src/main/java")
OUTPUT_DIRECTORY = Path("out")
MAIN_CLASS = "Duke"


@dataclass
class TestCase:
    """Stores one console test case parsed from the Markdown plan."""

    title: str
    aim: str
    user_input: str
    expected_output: str


def normalise_output(output: str) -> str:
    """Normalise line endings and an optional final newline for comparison."""
    return output.replace("\r\n", "\n").replace("\r", "\n").rstrip("\n")


def read_section(case_text: str, name: str) -> str:
    """Read one fenced text section from a Markdown test case."""
    pattern = re.compile(
        rf"^\*\*{re.escape(name)}:\*\*\s*\n```(?:text)?\n(.*?)\n```",
        re.MULTILINE | re.DOTALL,
    )
    match = pattern.search(case_text)
    if match is None:
        raise ValueError(f"Missing **{name}:** fenced block")
    return match.group(1)


def parse_test_cases(plan: str) -> list[TestCase]:
    """Parse all level-three test-case headings in the Markdown plan."""
    plan = re.sub(r"<!--.*?-->", "", plan, flags=re.DOTALL)
    pattern = re.compile(r"^###\s+(.+?)\n(.*?)(?=^###\s+|\Z)", re.MULTILINE | re.DOTALL)
    cases: list[TestCase] = []
    for match in pattern.finditer(plan):
        title, case_text = match.groups()
        aim_match = re.search(r"^\*\*Aim:\*\*\s*(.+)$", case_text, re.MULTILINE)
        if aim_match is None:
            raise ValueError(f"{title}: missing **Aim:**")
        cases.append(TestCase(
            title=title.strip(),
            aim=aim_match.group(1).strip(),
            user_input=read_section(case_text, "Input"),
            expected_output=read_section(case_text, "Expected output"),
        ))
    if not cases:
        raise ValueError("No test cases found. Add a level-three heading under 'Test cases'.")
    return cases


def check_java_version() -> None:
    """Require Java 25, as configured for this project."""
    result = subprocess.run(["java", "-version"], capture_output=True, text=True)
    version_text = result.stdout + result.stderr
    match = re.search(r'(?:version|openjdk)\s+"?(\d+)', version_text)
    if result.returncode != 0 or match is None or match.group(1) != "25":
        raise RuntimeError("Java 25 is required. Detected:\n" + version_text.strip())


def compile_program() -> None:
    """Compile every project Java source file into the output directory."""
    sources = sorted(SOURCE_DIRECTORY.rglob("*.java"))
    if not sources:
        raise RuntimeError(f"No Java sources found under {SOURCE_DIRECTORY}")
    OUTPUT_DIRECTORY.mkdir(exist_ok=True)
    result = subprocess.run(
        ["javac", "-d", str(OUTPUT_DIRECTORY), *(str(source) for source in sources)],
        capture_output=True,
        text=True,
    )
    if result.returncode != 0:
        raise RuntimeError("Compilation failed:\n" + result.stdout + result.stderr)


def display_session(case: TestCase, actual_output: str) -> None:
    """Print the captured input and output for one console session."""
    print(f"\n=== {case.title} ===")
    print(f"Aim: {case.aim}")
    print("Console input:")
    print(case.user_input)
    print("Console output:")
    print(actual_output, end="" if actual_output.endswith("\n") else "\n")


def run_test_case(case: TestCase) -> bool:
    """Run one isolated UI test and print its record and result."""
    result = subprocess.run(
        ["java", "-cp", str(OUTPUT_DIRECTORY), MAIN_CLASS],
        input=case.user_input + "\n",
        stdout=subprocess.PIPE,
        stderr=subprocess.STDOUT,
        text=True,
    )
    actual_output = result.stdout
    display_session(case, actual_output)

    if result.returncode == 0 and normalise_output(actual_output) == normalise_output(case.expected_output):
        print("Result: PASS")
        return True

    print("Result: FAIL")
    print("Expected output:")
    print(case.expected_output)
    print("Actual output:")
    print(actual_output, end="" if actual_output.endswith("\n") else "\n")
    if result.returncode != 0:
        print(f"Program exited with code {result.returncode}.")
    return False


def main() -> int:
    """Compile Duke and run plan cases until the first failure."""
    try:
        test_cases = parse_test_cases(PLAN_PATH.read_text(encoding="utf-8"))
        check_java_version()
        compile_program()
    except (OSError, RuntimeError, ValueError) as error:
        print(f"Test setup failed: {error}", file=sys.stderr)
        return 2

    for case in test_cases:
        if not run_test_case(case):
            return 1
    print(f"\nAll {len(test_cases)} UI test case(s) passed.")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
