# Content Sanitizer

A Python utility designed to moderate and sanitize text files, such as a dump of social media posts.

## Features
- **Banned Word Redaction**: Detects banned words (like *fake*, *spam*, *clickbait*, *hate*) and replaces them with asterisks `*` while preserving the context.
- **URL Extraction**: Automatically finds any HTTP or HTTPS links in the text and pulls them out.
- **Reporting**:
  - Saves the sanitized, safe text to a new file `moderation_report.txt`.
  - Saves the extracted URLs to `extracted_links.txt` for review.
  - Outputs a detailed summary of the moderation process to the console.

## Usage
Simply run the script using Python:
```bash
python sanitizer.py
```

It will process `sample_posts.txt` and generate the sanitized outputs automatically.
