package io.lino.anagram.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class AnagramServiceTest {

    /**
     * Tests the basic functionality of the anagram service:
     * - Are two strings that are anagrams detected as such?
     *
     * @param first
     * @param second
     */
    @ParameterizedTest
    @CsvSource({"listen,silent", "earth,heart", "acted,cadet", "save,vase", "stressed,desserts"})
    void areValidAnagramsTrue(String first, String second) {
        AnagramService anagramService = new AnagramService();
        assertTrue(anagramService.areAnagrams(first, second));
    }

    /**
     * Tests if the service detects invalid anagrams.
     *
     * @param first
     * @param second
     */
    @ParameterizedTest
    @CsvSource({"desert,dessert", "angle,anglo", "acted,actor", "save,sad", "stressed,deserts"})
    void areInvalidValidAnagramsFalse(String first, String second) {
        AnagramService anagramService = new AnagramService();
        assertFalse(anagramService.areAnagrams(first, second));
    }

    /**
     * Tests if the service ignores whitespace.
     *
     * @param first
     * @param second
     */
    @ParameterizedTest
    @CsvSource({"listen,silent", "earth,heart", "acted,cadet", "save,vase", "stressed,desserts"})
    void isWhitespaceIgnored(String first, String second) {
        AnagramService anagramService = new AnagramService();
        assertTrue(anagramService.areAnagrams(first+" ", second+"\t"));
    }

    /**
     * Tests if the service ignores capitalization.
     *
     * @param first
     * @param second
     */
    @ParameterizedTest
    @CsvSource({"Listen,Silent", "Earth,Heart", "Acted,Cadet", "Save,Vase", "Stressed,Desserts"})
    void isCapitalizationIrrelevant(String first, String second) {
        AnagramService anagramService = new AnagramService();
        assertTrue(anagramService.areAnagrams(first, second));
    }

    @Test
    void isHistoryFilterWorkingWithNoResults() {
        AnagramService anagramService = new AnagramService();
        anagramService.historizedAnagrams("a", "b");
        anagramService.historizedAnagrams("c", "d");
        assertEquals(0, anagramService.getAnagramsFromHistory("e").size());
    }

    @Test
    void isHistoryFilterWorkingWithResults() {
        AnagramService anagramService = new AnagramService();
        anagramService.historizedAnagrams("listen", "enlist");
        anagramService.historizedAnagrams("silent", "tinsel");
        anagramService.historizedAnagrams("sardine", "mackerel");
        assertEquals(3, anagramService.getAnagramsFromHistory("listen").size());
    }

    @Test
    void isHistoryFilterWorkingForEmptyResultset() {
        AnagramService anagramService = new AnagramService();
        anagramService.historizedAnagrams("listen", "enlist");
        anagramService.historizedAnagrams("silent", "tinsel");
        anagramService.historizedAnagrams("sardine", "mackerel");
        assertEquals(0, anagramService.getAnagramsFromHistory("sardine").size());
    }
}