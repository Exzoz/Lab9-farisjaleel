package edu.luc.cs271.myhashmap;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/** A map-based class or counting word frequencies from an iterator. */

public class WordCounter {

    /** The map for storing the word counts. */
    private final Map<String, Integer> theMap;

    /** Creates a word counter instance based on the given map. */
    public WordCounter(final Map<String, Integer> theMap) {
        this.theMap = theMap;
    }

    /** Counts the frequencies of all words in the given iterator. */
    public void countWords(final Iterator<String> words) {
        while (words.hasNext()) {
            String word = words.next();
            theMap.put(word, getCount(word) + 1);
        }
    }

    /** Retrieve the frequency of a particular word. */
    public int getCount(final String word) {
        return theMap.getOrDefault(word, 0);

    }

    /** Retrieve the map representing all word frequencies. */
    public Map<String, Integer> getCounts() {
        return Collections.unmodifiableMap(theMap);
    }
}
