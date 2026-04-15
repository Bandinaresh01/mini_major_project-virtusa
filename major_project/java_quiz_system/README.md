# Online Quiz System

A standalone interactive CLI Java application to manage and execute multiple-choice assessments. 

## Features
- **Admin Configuration**: Administrators can utilize the CLI interface dynamically to input Question strings, multiple choices, and configure the correct index on the spot.
- **Timing Mechanisms**: Captures internal Java `System.currentTimeMillis()` at the start and end of assessments to construct an accurate countdown/timer assessment.
- **Dynamic Scoring**: Automatically analyzes answer fidelity, computes final scoring percentage fractions, and offers subjective user-feedback based on thresholds.

## How to Compile & Run
```bash
javac QuizSystem.java
java QuizSystem
```
