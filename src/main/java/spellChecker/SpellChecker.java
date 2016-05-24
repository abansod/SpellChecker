package spellChecker;

import bloomFilter.BloomFilter;

import java.io.*;

/**
 * Created by Akky on 24-05-2016.
 */

public class SpellChecker {

    public static BloomFilter<String> dictionary = new BloomFilter<String>(340000);
    static {
        FileReader reader;
        try {
            String FILE = "D:\\Focus\\SpellChecker\\src\\main\\resources\\wordlist.txt";
            reader = new FileReader(new File(FILE));
            BufferedReader buffReader = new BufferedReader(reader);
            String word;

            while((word = buffReader.readLine())!=null){
                dictionary.add(word);
            }
            buffReader.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean verify(String word){
        return dictionary.contains(word);
    }


}
