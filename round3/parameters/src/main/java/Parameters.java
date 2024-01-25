/**
* Programming 3: Interfaces and Techniques. Spring 2024.

* @author Pradip Neupane (pradipneupane@tuni.fi).
* StudentId: 150426327
*/
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Parameters {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read parameters into a list
        ArrayList<String> parameters = readParameters(scanner);

        // Determine column widths
        int firstColumnWidth = determineFirstColumnWidth(parameters);
        int secondColumnWidth = determineSecondColumnWidth(parameters);

        // Print the table
        printTable(parameters, firstColumnWidth, secondColumnWidth);
    }

    private static ArrayList<String> readParameters(Scanner scanner) {
        System.out.println("Parameters:");
        ArrayList<String> parameters = new ArrayList<>();
        String input;
        while (!(input = scanner.nextLine()).isEmpty()) {
            parameters.add(input);
        }
        return parameters;
    }

    private static int determineFirstColumnWidth(ArrayList<String> parameters) {
        // Find the length of the widest value in the first column
        int maxFirstColumnWidth = 0;
        for (String parameter : parameters) {
            int width = String.valueOf(parameters.size()).length();
            if (width > maxFirstColumnWidth) {
                maxFirstColumnWidth = width;
            }
        }
        return maxFirstColumnWidth;
    }

    private static int determineSecondColumnWidth(ArrayList<String> parameters) {
        // Find the length of the widest value in the second column
        int maxSecondColumnWidth = 0;
        for (String parameter : parameters) {
            int width = parameter.length();
            if (width > maxSecondColumnWidth) {
                maxSecondColumnWidth = width;
            }
        }
        return maxSecondColumnWidth;
    }

    private static void printTable(ArrayList<String> parameters, int firstColumnWidth, int secondColumnWidth) {
        // Sort parameters alphabetically
        Collections.sort(parameters);

        // Print the table
        System.out.println("#########################################");
        for (int i = 0; i < parameters.size(); i++) {
            System.out.printf("# %-" + firstColumnWidth + "d | %-" + secondColumnWidth + "s #\n", i + 1, parameters.get(i));
            if (i < parameters.size() - 1) {
                System.out.println("#----+----------------------------------#");
            }
        }
        System.out.println("#########################################");
    }
}
