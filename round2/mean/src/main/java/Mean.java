
/**
* Programming 3: Interfaces and Techniques. Spring 2024.

* Exercise 2.4: Counting the Mean.
*
* @author Pradip Neupane (pradipneupane@tuni.fi).
* StudentId: 150426327
*/
import java.util.Scanner;
public class Mean {
    
    public static void main(String[] args) {
        System.out.println("Enter numbers:");
        
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        
        String[] numbers = line.split(" ");
        double sum = 0;
        
        for (String num : numbers)
            sum += Double.parseDouble(num);
        
        double mean = sum / numbers.length;
        System.out.println("Mean: " + mean);
    }
}
