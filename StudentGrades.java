import java.util.Scanner;

public class StudentGrades {

    public static double calculateAverage(int[] scores) {

        // Check if the array is null
        if (scores == null) {
            throw new IllegalArgumentException("Scores array cannot be null.");
        }

        // Check if the array is empty
        if (scores.length == 0) {
            throw new IllegalArgumentException("Scores array cannot be empty.");
        }

        int sum = 0;

        // Validate each score
        for (int score : scores) {

            if (score < 0) {
                throw new IllegalArgumentException(
                        "Score cannot be less than 0."
                );
            }

            if (score > 100) {
                throw new IllegalArgumentException(
                        "Score cannot be greater than 100."
                );
            }

            sum += score;
        }

        double average = (double) sum / scores.length;

        // Check for significant failure
        if (average < 40) {
            throw new FailedSignificantlyException(
                    "Student average is " + average +
                            ", which is a significant failure."
            );
        }

        return average;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter scores (comma-separated): ");

            String input = scanner.nextLine();

            // Split input by comma
            String[] parts = input.split(",");

            int[] scores = new int[parts.length];

            // Convert String values to integers
            for (int i = 0; i < parts.length; i++) {
                scores[i] = Integer.parseInt(parts[i].trim());
            }

            // Calculate average
            double average = calculateAverage(scores);

            System.out.println("Average score: " + average);

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid input: Please enter a valid number for scores. "
                            + e.getMessage()
            );

        } catch (FailedSignificantlyException e) {

            System.out.println(
                    "Significant Failure: " + e.getMessage()
            );

        } catch (IllegalArgumentException e) {

            System.out.println("Error: " + e.getMessage());

        } finally {

            scanner.close();

            System.out.println(
                    "Grade calculation process concluded."
            );
        }
    }
}