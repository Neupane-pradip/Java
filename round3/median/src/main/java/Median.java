/**
* Programming 3: Interfaces and Techniques. Spring 2024.

* Exercise 2.4: Counting the Mean.
*
* @author Pradip Neupane (pradipneupane@tuni.fi).
* StudentId: 150426327
*/
import java.util.Arrays;
import java.util.Scanner;

public class Median {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read numbers into an array
        System.out.println("Enter numbers:");
        double[] numbers = readNumbers(scanner);

        // Calculate and print the median
        double median = calculateMedian(numbers);
        System.out.printf("Median: %.5f%n", median);
    }

    private static double[] readNumbers(Scanner scanner) {
        String input = scanner.nextLine();
        String[] numberStrings = input.split("\\s+");
        double[] numbers = new double[numberStrings.length];
        for (int i = 0; i < numberStrings.length; i++) {
            numbers[i] = Double.parseDouble(numberStrings[i]);
        }
        return numbers;
    }

    private static double calculateMedian(double[] numbers) {
        Arrays.sort(numbers);

        int length = numbers.length;
        if (length % 2 == 0) {
            // If the number of elements is even, calculate the average of the middle two elements
            int middle1 = length / 2 - 1;
            int middle2 = length / 2;
            return (numbers[middle1] + numbers[middle2]) / 2.0;
        } else {
            // If the number of elements is odd, return the middle element
            int middle = length / 2;
            return numbers[middle];
        }
    }
}
