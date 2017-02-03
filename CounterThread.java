import java.lang.Thread;
import java.lang.InterruptedException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;


public class CounterThread extends Thread {
  static Semaphore mutex = new Semaphore(1);
  static Hashtable<String, Integer> master = new Hashtable<String, Integer>();
  File file;
  
  /*
   * file is the assigned file for this thread to count
   */ 
  public CounterThread(File f){
    super();
    this.file = f;
  }
  
  public void run(){
    
    Hashtable<String, Integer> table = countWordsInFile(file);
    try{
     // System.out.println("acquiring mutex");
      mutex.acquire();
      mergeWithMaster(table);
     // System.out.println("releasing mutex");
      mutex.release();
    } catch(InterruptedException ex){
      System.err.print(ex);
    }
    
    
  }
  
  public Hashtable<String, Integer> countWordsInFile(File file){
    Hashtable<String, Integer> table = new Hashtable<String, Integer>();
    try {
      
      Scanner sc = new Scanner(file);
      
      String words;
      
      while (sc.hasNext()) {
        
        words = sc.next();
        //System.out.println(words);
        words = words.toLowerCase();
        words = words.replaceAll("[^a-zA-Z ]", "");
        //System.out.println(words);
        
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
    return table;
  }
  
  private void mergeWithMaster(Hashtable<String, Integer> table){
    
    Set<String> keys = table.keySet();
    Iterator<String> iter = keys.iterator();
    
    while (iter.hasNext()){
      String word = iter.next();
      //System.out.println(word);
      if (master.containsKey(word)) {
        master.put(word, table.get(word) + master.get(word));
      } else {
        master.put(word, table.get(word));
      }
    }
   // show(master);
  }
  
  
  
  
  public static void show(Hashtable<String, Integer> table) {
    
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
  
  public static void output(String outputFileName){
  File outputFile = new File(outputFileName);
    try{
    FileWriter writeOutputFile = new FileWriter(outputFile);
    
    Enumeration<String> entries = master.keys();
    System.out.println("Enum" + entries.hasMoreElements() );
    while(entries.hasMoreElements()){
      String next = entries.nextElement();
      writeOutputFile.write(next + ":\t\t" + master.get(next)+"\n");
      System.out.println(next + ":\t\t" + master.get(next)+"\n");
  }
    writeOutputFile.close();
    }catch(IOException ex){
      System.err.println(ex);
    }
  }
  
}