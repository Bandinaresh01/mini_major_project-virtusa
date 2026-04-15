import os

def analyze_logs(input_file, output_file):
    if not os.path.exists(input_file):
        print(f"Error: {input_file} not found.")
        return

    # Keywords to filter
    target_keywords = ["ERROR", "CRITICAL", "FAILED LOGIN"]
    counts = {kw: 0 for kw in target_keywords}
    
    try:
        with open(input_file, 'r') as infile, open(output_file, 'w') as outfile:
            for line in infile:
                for kw in target_keywords:
                    if kw in line:
                        counts[kw] += 1
                        outfile.write(line)
                        break # Avoid duplicate write if a line has multiple keywords

        print("--- OpsBot Log Analysis Complete ---")
        print("Summary of Issues Found:")
        for kw, count in counts.items():
            print(f"  {kw}: {count}")
            
        file_size = os.path.getsize(output_file)
        print(f"\nFiltered logs saved to '{output_file}'.")
        print(f"Output file size: {file_size} bytes.")
        
    except Exception as e:
        print(f"An error occurred during log processing: {e}")

if __name__ == "__main__":
    input_log = "server_logs.txt"
    output_log = "filtered_logs.txt"
    analyze_logs(input_log, output_log)
