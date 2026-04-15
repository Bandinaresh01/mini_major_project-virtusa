import re

def extract_keywords(text):
    # Very basic tokenization and cleaning as a fallback NLP simulator without needing NLTK/Spacy installs
    text = text.lower()
    words = re.findall(r'\b[a-z]{2,}\b', text)
    # Generic stop words to ignore
    stop_words = {'and', 'the', 'for', 'with', 'this', 'that', 'you', 'are', 'not', 'but'}
    keywords = {word for word in words if word not in stop_words}
    return keywords

def analyze_resume(resume_file, job_description):
    print("--- NLP Resume Analyzer Started ---")
    
    try:
        with open(resume_file, 'r', encoding='utf-8') as f:
            resume_text = f.read()
    except FileNotFoundError:
        print(f"Error: {resume_file} not found.")
        return

    print("Extracting keywords from Job Description...")
    job_keywords = extract_keywords(job_description)
    
    print("Extracting keywords from Resume...")
    resume_keywords = extract_keywords(resume_text)

    # Calculate match
    matched_skills = job_keywords.intersection(resume_keywords)
    missing_skills = job_keywords.difference(resume_keywords)
    
    match_score = (len(matched_skills) / len(job_keywords)) * 100 if job_keywords else 0

    print("\n================ MATCH REPORT ================")
    print(f"Match Score: {match_score:.2f}%\n")
    print(f"Matched Keywords ({len(matched_skills)}):")
    print("  " + ", ".join(matched_skills))
    
    if missing_skills:
        print(f"\nMissing Skills / Keywords ({len(missing_skills)}):")
        print("  " + ", ".join(missing_skills))
        print("-> Suggestion: Consider adding these missing skills to improve your match rate!")
    print("==============================================")

if __name__ == "__main__":
    job_desc = """
    We are looking for a Software Engineer with strong skills in Python, Java, SQL, and Machine Learning.
    Experience with React, AWS, Docker, and CI/CD pipelines is a huge plus.
    Excellent communication and teamwork required.
    """
    analyze_resume("dummy_resume.txt", job_desc)
