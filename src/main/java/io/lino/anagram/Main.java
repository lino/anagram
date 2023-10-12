package io.lino.anagram;

import io.lino.anagram.services.AnagramService;

/**
 * Interface for the Anagram Service.
 */
public class Main {
    public static void main(String[] args) {
        // Parse the command line arguments and check if there are two strings
        if (args.length != 2) {
            System.out.println("Please provide two strings as arguments.");
            System.exit(1);
        }

        // Create an instance of the AnagramService
        AnagramService anagramService = new AnagramService();
        anagramService.areAnagrams(args[0], args[1]);
    }
}