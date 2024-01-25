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
    for (int i = 1; i <= parameters.size(); i++) {
        int width = String.valueOf(i).length();
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

   // Print the table
    private static void printTable(ArrayList<String> parameters, int firstColumnWidth, int secondColumnWidth) {
    // Sort parameters alphabetically
    Collections.sort(parameters);

    // Determine the length of the longest parameter for the outer border
    int maxParameterWidth = parameters.stream().mapToInt(String::length).max().orElse(0);

    // Print the outer border
    System.out.println("#".repeat(maxParameterWidth + firstColumnWidth + 6)+ "#"); // 6 is for extra padding and vertical bars

    // Print the table content
    for (int i = 0; i < parameters.size(); i++) {
        // Print the outer border of the row
        System.out.print("#");

        // Print the first column (aligned to the right)
        System.out.printf(" %" + firstColumnWidth + "d |", i + 1);

        
        // Print the second column (aligned to the left)
        System.out.printf(" %-" + secondColumnWidth + "s ", parameters.get(i));

        // Print the outer border of the row
        System.out.println("#");

        // Print the inner border between rows (except for the last row)
        if (i < parameters.size() - 1) {
            System.out.println("#" + "-".repeat(firstColumnWidth + 2) + "+" + "-".repeat(secondColumnWidth + 2) + "#");
        }
    }

    // Print the bottom outer border
    System.out.println("#".repeat(maxParameterWidth + firstColumnWidth + 6)+ "#");
    }
}


