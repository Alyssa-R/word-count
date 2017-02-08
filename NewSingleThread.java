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

public class NewSingleThread{
  
  public static File[] createFileList(String directory) {
    TextFileNameFilter textFilter = new TextFileNameFilter(); //only get .txt files
    File[] files = new File(directory).listFiles(textFilter); //get .txt files from specifed directory
    
    //System.out.println(files.length);
    for(int i=0; i < files.length; i++){
      //System.out.println(files[i].toString());
      
    }
    return files;
  }
public static void output(String outputFileName, Hashtable<String, Integer> table){
    File outputFile = new File(outputFileName);
    try{
    FileWriter writeOutputFile = new FileWriter(outputFile);
    
    Enumeration<String> entries = table.keys();
    while(entries.hasMoreElements()){
      String next = entries.nextElement();
      
      writeOutputFile.write(next + ":\t\t" + table.get(next)+"\n");
  }
    writeOutputFile.close();
    }catch(IOException ex){
      System.err.println(ex);
    }
  }
public static Hashtable<String, Integer> countWordsInMultipleFiles(File[] fileArray){
    Hashtable<String, Integer> table = new Hashtable<String, Integer>();
    //System.out.println("countWordsInMultipleFiles" + fileArray.length);
    for(int i = 0; i < fileArray.length; i++){
      try {
        
        Scanner sc = new Scanner(fileArray[i]);
        
        String words;
        
        while (sc.hasNext()) {
          
          words = sc.next();
          words = words.toLowerCase(); // make lowercase
          words = words.replaceAll("[^a-zA-Z ]", ""); // remove special characters
          
          if (table.containsKey(words)) {
            table.put(words, table.get(words) + 1); // if the word is already in the hashtable, increment the occurence value
          } else {
            table.put(words, 1); // if the word isn't in the hashtable, put it in
          }
        }
        sc.close();
        
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }
    return table;
  }
public static void main (String[] args){
    long startTime = System.currentTimeMillis();
    //int threadCount = Integer.parseInt(args[0]);
    //String outputFileName = args[1];
    
    //NewSingleThread t1 = new NewSingleThread();
    
    File[] files = createFileList("gutenberg-testing");
    
    //CounterThread[] threads = t1.assignThreads(files,threadCount, outputFileName);
    
    //for(int i = 0; i < threads.length; i++){
     // threads[i].start();
    //}
  
   Hashtable<String, Integer> table = countWordsInMultipleFiles(files);
   output("output_singlethread.txt", table);
    long endTime   = System.currentTimeMillis();
    long totalTime = endTime - startTime;
    System.out.println(totalTime);
  }
}