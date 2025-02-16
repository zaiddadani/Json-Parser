package jsonParser;
import java.util.Scanner;

public class JsonCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JsonParser parser = new JsonParser();

        System.out.println("Welcome to the JSON Parser CLI!");
        System.out.println("Type 'exit' to quit.");

        while (true) {
            System.out.print("\nEnter JSON string: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            try {
                Object result = parser.parse(input);
                System.out.println("Parsed Output: " + result);
            } catch (Exception e) {
                System.out.println("Error parsing JSON: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
