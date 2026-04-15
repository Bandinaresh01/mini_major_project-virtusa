import re
import os

def sanitize_content(input_file, report_file, links_file):
    if not os.path.exists(input_file):
        print(f"Error: {input_file} not found.")
        return

    # List of banned words to redact
    banned_words = ["spam", "scam", "clickbait", "fake", "hate"]
    
    # Regex to match URLs (simple URL regex)
    url_pattern = re.compile(r'https?://[^\s]+')
    
    total_posts = 0
    banned_words_replaced = 0
    extracted_urls = []

    try:
        with open(input_file, 'r', encoding='utf-8') as infile, \
             open(report_file, 'w', encoding='utf-8') as report:
             
             for line in infile:
                 total_posts += 1
                 original_line = line.strip()
                 if not original_line:
                     continue
                     
                 # Extract URLs
                 urls_in_line = url_pattern.findall(original_line)
                 extracted_urls.extend(urls_in_line)
                 
                 # Sanitize banned words (case-insensitive)
                 sanitized_line = original_line
                 for word in banned_words:
                     # Using regex to replace case-insensitive exact matches
                     # \b is word boundary
                     pattern = re.compile(rf'\b{word}\b', re.IGNORECASE)
                     sanitized_line, count = pattern.subn("*" * len(word), sanitized_line)
                     banned_words_replaced += count
                     
                 report.write(sanitized_line + "\n")

        # Write extracted URLs to file
        with open(links_file, 'w', encoding='utf-8') as lfile:
            for url in extracted_urls:
                lfile.write(url + "\n")

        # Print Moderation Report Summary
        print("------------- MODERATION REPORT -------------")
        print(f"Total Posts Scanned : {total_posts}")
        print(f"Banned Words Redacted: {banned_words_replaced}")
        print(f"URLs Extracted       : {len(extracted_urls)}")
        print("---------------------------------------------")
        print(f"Sanitized content saved to '{report_file}'")
        print(f"Extracted URLs saved to '{links_file}'")

    except Exception as e:
         print(f"Error processing files: {e}")

if __name__ == "__main__":
    input_txt = "sample_posts.txt"
    report_txt = "moderation_report.txt"
    links_txt = "extracted_links.txt"
    
    print("Social Media Content Sanitizer Started...\n")
    sanitize_content(input_txt, report_txt, links_txt)
