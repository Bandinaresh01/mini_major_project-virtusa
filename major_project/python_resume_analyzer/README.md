# Resume Analyzer & Job Matcher

A heuristic Python NLP analysis tool that compares the text contents of an applicant's resume to an inputted Job Description prompt in order to score the candidacy.

## Features
- **Tokenization & Stop Words**: Implements clean tokenization rules via `Regex` and filtering out grammatical stop-words (to simulate NLP frameworks like Spacy/NLTK natively without strict system dependencies).
- **Match Metric Scoring**: Provides a quantitative % match based on skill intersection between the CV and Job Description.
- **Actionable AI Feedback**: Identifies the specifically missing high-value keywords that a candidate could theoretically add to boost ATS (Applicant Tracking System) hits.

## Usage
Simply run the script via terminal. It automatically reads `dummy_resume.txt`:
```bash
python resume_analyzer.py
```
