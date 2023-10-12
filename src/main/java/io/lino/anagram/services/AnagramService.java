package io.lino.anagram.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This service should check if two strings are anagrams.
 */
public class AnagramService {

    private Set<String> inputHistory;

    public AnagramService() {
        inputHistory = new HashSet<>();
    }

    public String anagramHash(String input) {
        char[] charArray = input.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }

    /**
     * This method should check if two strings are anagrams.
     * @param firstString
     * @param secondString
     * @return true if the strings are anagrams, false otherwise
     */
    public boolean areAnagrams(String firstString, String secondString) {

        // Remove any whitespace and convert strings to lowercase
        String str1 = firstString.replaceAll("\\s", "").toLowerCase();
        String str2 = secondString.replaceAll("\\s", "").toLowerCase();

        // Add the input to the history
        inputHistory.add(firstString);
        inputHistory.add(secondString);

        // Check if length is same, if not, it can logically be no anagram.
        if (str1.length() != str2.length()) {
            return false;
        }

        // Convert strings to character array
        char[] charArray1 = str1.toCharArray();
        char[] charArray2 = str2.toCharArray();

        // Sort the char array
        Arrays.sort(charArray1);
        Arrays.sort(charArray2);

        // Check if sorted char arrays are same
        return Arrays.equals(charArray1, charArray2);
    }

    /**
     * Takes the input and returns a set of anagrams from the history set.
     * @param input
     * @return
     */
    public Set<String> getAnagramsFromHistory(String input) {
        Set<String> anagrams = inputHistory.stream().filter(s -> areAnagrams(s, input)).collect(Collectors.toSet());
        anagrams.remove(input);
        return anagrams;
    }

}
