package io.lino.anagram;

import io.lino.anagram.services.AnagramService;

import java.util.Scanner;
import java.util.Set;

/**
 * Interface for the Anagram Service.
 */
public class Main {

    private static final String EXIT_COMMAND = "/exit";
    private static final String HELP_COMMAND = "/help";
    private static final String SEARCH_COMMAND = "/search";

    private final AnagramService service = new AnagramService();


    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Anagram Service!");
        System.out.println("Type '" + HELP_COMMAND + "' for a list of available commands.");
        System.out.println("Enter pairs of strings to check if they are anagrams.");
        System.out.println("Terminate each input with ENTER.");

        while (true) {
            System.out.println("Enter first string:");
            String firstString = scanner.nextLine();

            if (handleSpecialCommands(firstString)) {
                continue;
            }

            System.out.println("Enter second string:");
            String secondString = scanner.nextLine();

            if (handleSpecialCommands(secondString)) {
                continue;
            }

            boolean result = service.historizedAnagrams(firstString, secondString);

            if (result) {
                System.out.println("The strings are anagrams!");
            } else {
                System.out.println("The strings are not anagrams!");
            }
        }
    }

    private boolean handleSpecialCommands(String input) {
        switch (input) {
            case EXIT_COMMAND:
                System.out.println("Exiting application...");
                System.exit(0);
                return true;
            case HELP_COMMAND:
                displayHelp();
                return true;
            case SEARCH_COMMAND:
                searchInputHistory();
                return true;
            default:
                return false;
        }
    }

    private void displayHelp() {
        System.out.println("Available commands:");
        System.out.println("- " + EXIT_COMMAND + ": Exit the application");
        System.out.println("- " + HELP_COMMAND + ": Display this help message");
        System.out.println("- " + SEARCH_COMMAND + ": Search in the input history for anagrams");
    }

    private void searchInputHistory() {
        System.out.println("Enter search term:");
        Scanner scanner = new Scanner(System.in);
        String searchTerm = scanner.nextLine();

        Set<String> results = this.service.getAnagramsFromHistory(searchTerm);

        if (results.isEmpty()) {
            System.out.println("No results found.");
            return;
        }

        // Print the result count and the results
        System.out.println("Found " + results.size() + " results:");
        results.forEach(System.out::println);
    }
}