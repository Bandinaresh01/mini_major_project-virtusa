import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Question {
    String text;
    String[] options;
    int correctOption;

    public Question(String text, String[] options, int correctOption) {
        this.text = text;
        this.options = options;
        this.correctOption = correctOption; // 1-indexed for ease of user input
    }
}

public class QuizSystem {
    private List<Question> questions = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void addQuestion() {
        System.out.println("\n--- Admin: Add Question ---");
        System.out.println("Enter question text: ");
        String text = scanner.nextLine();

        String[] options = new String[4];
        for (int i = 0; i < 4; i++) {
            System.out.print("Enter Option " + (i + 1) + ": ");
            options[i] = scanner.nextLine();
        }

        System.out.print("Enter Correct Option Number (1-4): ");
        int correct = Integer.parseInt(scanner.nextLine());

        questions.add(new Question(text, options, correct));
        System.out.println("Question added successfully!");
    }

    public void takeQuiz() {
        if (questions.isEmpty()) {
            System.out.println("No questions available! Ask the admin to add some.");
            return;
        }

        System.out.println("\n--- Starting Quiz ---");
        int score = 0;
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("\nQ" + (i + 1) + ": " + q.text);
            for (int j = 0; j < q.options.length; j++) {
                System.out.println("  " + (j + 1) + ". " + q.options[j]);
            }
            
            System.out.print("Your Answer (1-4): ");
            int answer = Integer.parseInt(scanner.nextLine());
            
            if (answer == q.correctOption) {
                score++;
            }
        }

        long endTime = System.currentTimeMillis();
        long durationSeconds = (endTime - startTime) / 1000;

        System.out.println("\n=== RESULT SUMMARY ===");
        System.out.println("Time taken: " + durationSeconds + " seconds");
        System.out.println("Score: " + score + " / " + questions.size());
        double percentage = ((double) score / questions.size()) * 100;
        System.out.println("Percentage: " + String.format("%.2f", percentage) + "%");
        
        if (percentage == 100) {
            System.out.println("Performance: Excellent!");
        } else if (percentage >= 50) {
            System.out.println("Performance: Good.");
        } else {
            System.out.println("Performance: Needs Improvement.");
        }
    }

    public void start() {
        // Pre-fill some default questions to demo easily
        questions.add(new Question("What is the capital of France?", new String[]{"London", "Berlin", "Paris", "Madrid"}, 3));
        questions.add(new Question("Which Java keyword is used to inherit a class?", new String[]{"implement", "extends", "inherits", "super"}, 2));
        
        while (true) {
            System.out.println("\n--- Online Quiz & Assessment System ---");
            System.out.println("1. Admin: Add Question");
            System.out.println("2. Student: Take Quiz");
            System.out.println("3. Exit");
            System.out.print("Choice: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                addQuestion();
            } else if (choice.equals("2")) {
                takeQuiz();
            } else if (choice.equals("3")) {
                break;
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    public static void main(String[] args) {
        QuizSystem q = new QuizSystem();
        q.start();
    }
}
