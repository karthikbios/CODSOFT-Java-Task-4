import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class JavaQuizApplication {
    private static final int TOTAL_QUESTIONS = 5;
    private static final int TIME_LIMIT_PER_QUESTION = 10; // Time limit per question in seconds
    private static int score = 0;
    private static int currentQuestionIndex = 0;
    private static Timer timer;
    private static Scanner scanner = new Scanner(System.in);

    private static String[] questions = {
            "What is the main purpose of the 'public static void main(String[] args)' method in Java?",
            "Which keyword is used to declare a constant in Java?",
            "What is the difference between '== and '.equals()' in Java when comparing strings?",
            "In Java, what is the purpose of the 'super' keyword?",
            "Which collection class in Java is synchronized?"
    };

    private static String[][] options = {
            {"a. To declare variables", "b. To execute the program", "c. To print output to the console", "d. To initialize the class"},
            {"a. const", "b. final", "c. static", "d. constant"},
            {"a. '==' compares object references, '.equals()' compares content", "b. '==' compares content, '.equals()' compares object references", "c. '==' is used for primitive types, '.equals()' is used for objects", "d. '==' and '.equals()' are interchangeable in all cases"},
            {"a. To call the superclass constructor", "b. To refer to the current instance of the class", "c. To access the superclass's static members", "d. To indicate an abstract method in the superclass"},
            {"a. ArrayList", "b. HashMap", "c. Vector", "d. LinkedList"}
    };

    private static int[] correctAnswers = {1, 2, 0, 0, 2};

    public static void main(String[] args) {
        startQuiz();
    }

    private static void startQuiz() {
        System.out.println("Welcome to the Java Programming Quiz!");

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up! Moving to the next question.");
                showNextQuestion();
            }
        }, TIME_LIMIT_PER_QUESTION * 1000);

        showNextQuestion();
    }

    private static void showNextQuestion() {
        if (currentQuestionIndex < TOTAL_QUESTIONS) {
            System.out.println("\nQuestion " + (currentQuestionIndex + 1) + ": " + questions[currentQuestionIndex]);

            for (int i = 0; i < options[currentQuestionIndex].length; i++) {
                System.out.println(options[currentQuestionIndex][i]);
            }

            System.out.print("Your choice (Enter a, b, c, d or 0 to skip): ");

            try {
                char userChoice = scanner.next().toLowerCase().charAt(0);

                if (userChoice == '0') {
                    System.out.println("Skipping to the next question.");
                    currentQuestionIndex++;
                    showNextQuestion();
                } else if (userChoice >= 'a' && userChoice <= 'd') {
                    stopTimer(); // Stop the timer when the user provides an answer
                    checkAnswer(userChoice - 'a');
                    currentQuestionIndex++;
                    startQuiz();
                } else {
                    System.out.println("Invalid choice. Moving to the next question.");
                    currentQuestionIndex++;
                    startQuiz();
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Moving to the next question.");
                currentQuestionIndex++;
                startQuiz();
            }
        } else {
            endQuiz();
        }
    }

    private static void checkAnswer(int userChoice) {
        if (userChoice == correctAnswers[currentQuestionIndex]) {
            System.out.println("Correct! You earned a point.");
            score++;
        } else {
            System.out.println("Incorrect. The correct answer was: " + options[currentQuestionIndex][correctAnswers[currentQuestionIndex]]);
        }
    }

    private static void endQuiz() {
        System.out.println("\nQuiz completed!");
        System.out.println("Your final score: " + score + " out of " + TOTAL_QUESTIONS);
        timer.cancel();
        System.exit(0);
    }

    private static void stopTimer() {
        timer.cancel();
    }
}
