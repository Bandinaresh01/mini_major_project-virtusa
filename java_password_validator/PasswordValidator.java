import java.util.Scanner;

public class PasswordValidator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- SafeLog Password Validator ---");
        
        while (true) {
            System.out.print("Enter your new password: ");
            String password = scanner.nextLine();
            
            String feedback = validatePassword(password);
            
            if (feedback.equals("Valid")) {
                System.out.println("Success! Password is valid and secure.");
                break;
            } else {
                System.out.println("Invalid Password: " + feedback);
                System.out.println("Please try again.\n");
            }
        }
        scanner.close();
    }

    public static String validatePassword(String password) {
        if (password.length() < 8) {
            return "Must be at least 8 characters long.";
        }
        
        boolean hasUppercase = false;
        boolean hasDigit = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }
        }
        
        if (!hasUppercase) {
            return "Missing at least one uppercase letter.";
        }
        
        if (!hasDigit) {
            return "Missing at least one digit.";
        }
        
        return "Valid";
    }
}
