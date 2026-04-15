# OpsBot Log Analyzer

A Python utility that automatically parses server log files, filters out important alert messages, and saves the summarized results.

## Features
- **Filtering**: Reads through a standard text-based log file and identifies lines containing specific keywords (`ERROR`, `CRITICAL`, or `FAILED LOGIN`).
- **Counting**: Tracks the frequency of each incident type.
- **Reporting**: Prints a consolidated summary of incidents and generates a new text file containing only the filtered alerts.
- **Size Checking**: Measures and prints the physical size of the newly generated log file.

## Setup
Ensure that `server_logs.txt` exists in the same directory. A dummy log file is provided with the project.

## Usage
Run the script using Python:
```bash
python log_analyzer.py
```

It will produce an output file named `filtered_logs.txt` containing the isolated critical logs.
