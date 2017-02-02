import java.util.*;
import java.io.*;

public class WordCountSingleThread {
  
  private Hashtable<String, Integer> table = new Hashtable<String, Integer>();
  
  public void readFile() {
    
    File file = new File("test-text/test1.txt");
    
    try {
      
      Scanner sc = new Scanner(file);
      
      String words;
      
      while (sc.hasNext()) {
        
        words = sc.next();
        //System.out.println(words);
        words = words.toLowerCase();
        words = words.replaceAll("[^a-zA-Z ]", "");
        System.out.println(words);
        
        //if (words.length() >= 2) {
        //table.put(words, 1);
        //add(words);
        //}
        
        if (table.containsKey(words)) {
          table.put(words, table.get(words) + 1);
        } else {
          table.put(words, 1);
        }
        
      }
      sc.close();
      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
  
  public void add(String words) {
    
    Set<String> keys = table.keySet();
    for (String count : keys) {
      if (table.containsKey(count)) {
        table.put(count, table.get(count) + 1);
      } else {
        table.put(count, 1);
      }
    }
  }
  
  public void show() {
    
    // for(Entry<String, Integer> entry : table.entrySet()) {
    // System.out.println(entry.getKey() + "\t" + entry.getValue());
    //}
    //for(String entry : table.key
    
    Enumeration<String> entries = table.keys();
    while(entries.hasMoreElements()){
      String next = entries.nextElement();
      
      System.out.println(next + ",\t" + table.get(next));
    }
  }
  
  public static void main(String args[]) {
    
    WordCountSingleThread abc = new WordCountSingleThread();
    
    abc.readFile();
    
    abc.show();
  }
}