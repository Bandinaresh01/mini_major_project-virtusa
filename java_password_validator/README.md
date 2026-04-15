# SafeLog Password Validator

A Java command-line application that validates a newly chosen password against strict security requirements.

## Requirements
To be considered valid, a password must:
- Be at least 8 characters long.
- Contain at least one uppercase letter.
- Contain at least one digit (number).

The program uses a continuous loop to ask for input until a valid password is provided, returning specific feedback (e.g., "Missing digit") on each failed attempt.

## Setup and Execution
1. Ensure you have the Java Development Kit (JDK) installed.
2. Compile the program:
   ```bash
   javac PasswordValidator.java
   ```
3. Run the program:
   ```bash
   java PasswordValidator
   ```

## Output Example
```
--- SafeLog Password Validator ---
Enter your new password: pass
Invalid Password: Must be at least 8 characters long.
Please try again.

Enter your new password: password
Invalid Password: Missing at least one uppercase letter.
Please try again.

Enter your new password: Password
Invalid Password: Missing at least one digit.
Please try again.

Enter your new password: Password123
Success! Password is valid and secure.
```
