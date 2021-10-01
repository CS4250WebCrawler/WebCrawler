package count;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Count
{
    static void CountWords(String filename, Map<String, Integer> words) throws FileNotFoundException
    {
        Scanner file = new Scanner (new File(filename));
        while(file.hasNext()){
            String word = file.next();
            
            Integer count = words.get(word.replaceAll("\\p{Punct}","").toLowerCase());
            if(count != null)
                count++;
            else
                count = 1;
            words = words.entrySet().stream()
            .sorted(Comparator.comparingInt(e -> e.getValue()))
            .collect(Collectors.toMap((entry) -> entry.getKey(), (entry) -> entry.getValue(),
                (a, b) -> { throw new AssertionError(); },
                LinkedHashMap::new
        ));

        words.entrySet().forEach(System.out::println);
        words.put(word.toLowerCase(), count);
        }
        file.close();
    }
    public static void main(String[] args) throws FileNotFoundException {
        Map<String,Integer> words=new HashMap<String, Integer>();
        
        File folder = new File("text - en.zip");
        File[] listOfFiles = folder.listFiles();
        
        for (int i = 0; i < listOfFiles.length; i++) {
            File file = listOfFiles[i];
            if (file.isFile() && file.getName().endsWith(".txt")) {
                //FileUtils?
                String content = FileUtils.readFileToString(file);
        }
            //replace 2.txt with text - en.zip
        CountWords("2.txt",words);
        System.out.println(words);
    }
} 
