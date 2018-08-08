package edu.luc.cs271.myhashmap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collector;

public class Main {

    public static void main(final String[] args) throws InterruptedException, FileNotFoundException {
        File file = new File("src/book.rtf");
        final Scanner input = new Scanner (file).useDelimiter("[^\\p{Alnum}]+");

        final long time0 = System.currentTimeMillis(); //current time

        //create instance of wordCounter, It can be MyHashMap, HashMap, or TreeMap
        WordCounter wordCounter = new WordCounter(new MyHashMap<>(6007));

        //count words
        wordCounter.countWords(input);
        Map<String, Integer> map = wordCounter.getCounts();
        System.out.println("Map size: " + map.size());

        //move elements from map to list
        List<Entry<String, Integer>> list = new ArrayList(map.size());
        list.addAll(map.entrySet());
        System.out.println("time in ms: " + (System.currentTimeMillis() -time0)); //time elapsed since above

        //sort list and print in descending order by number of occurences
        Collections.sort(list, new DescendingByCount());
        for (int i = 0; i < 10; i++) {
            System.out.println(list.get(i).getValue() + "=" + list.get(i).getKey());
        }
    }
}

