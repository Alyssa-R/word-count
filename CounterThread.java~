/* Hannah Murphy and Alyssa Rivera
 * CS 349a - Assignment 1
 * Febrary 7, 2017
 * 
 * CounterThread.java
 * 
 * Instantiates Thread that receives an array of Files and creates a hash map of word occurences in all files.
 */


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
  
  static Semaphore mutex = new Semaphore(1); // requires that only one thread is merging with master hahtable at a time
  static Hashtable<String, Integer> master = new Hashtable<String, Integer>(); //master hash table that keeps track of all of the word occurences
  static int threadCount; //number of threa, specified by user 
  static String outputFileName;//file to write final master hanshtable to, specified by user
  static Semaphore allDone = new Semaphore(threadCount); //requires that all threads have merged with master hashtable before writing output file
  
  File file; //this will only be used if the first constructor is used
  File[] fileArray; //the file array for the current thread to process
  
  /* THIS CONSTRUCTOR IS NOT USED IN CURRENT WORDCOUNTMULTITHREAD IMPLEMENTATION
   * file is the assigned file for this thread to count
   */ 
  public CounterThread(File f, int tc, String ofn){
    super();
    this.file = f;
    this.threadCount = tc;
    this.outputFileName = ofn;
  }
  
  
  /* Constructor
   * 
   * Creates a CounterThread object that processes an array of Files into a hashtable of their word frequencies
   * upon calling start()
   * 
   * @param: fileArray: array of files to be processed into hashtable
   * @param: threadCount: total number of threads merging into the master hashtable
   * @param: outputFileName: filename to write the final master hashtable to
   */
  public CounterThread(File[] f, int tc, String ofn){
    super();
    this.fileArray = f;
    this.threadCount = tc;
    this.outputFileName = ofn;
  }
  
  
  /* run()
   * 
   * Creates a hashtable of all the words in the file array. Once the mutex semaphore is available, the newly created
   * hashtable is merged with the master hashtable. When all the threads have release the allDone semaphore, one 
   * thread acquires it and writes the output file.
   */
  public void run(){
    
    Hashtable<String, Integer> table = countWordsInMultipleFiles(fileArray);
    
    try{
     // System.out.println("acquiring mutex");
      mutex.acquire();
      mergeWithMaster(table);
     // System.out.println("releasing mutex");
      mutex.release();
      allDone.release();
      
      allDone.acquire();
      output(outputFileName);
      
    }catch(InterruptedException ex){
      System.err.print(ex);
    }
  }
  
  
  /*countWordsInFile()
   * 
   * @param: file - the 
   */ 
  public Hashtable<String, Integer> countWordsInFile(File file){
    Hashtable<String, Integer> table = new Hashtable<String, Integer>();
    try {
      
      Scanner sc = new Scanner(file);
      
      String words;
      
      while (sc.hasNext()) {
        
        words = sc.next();
        words = words.toLowerCase();
        words = words.replaceAll("[^a-zA-Z ]", "");
        
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
  
  /*countWordsInFile()
   * 
   * Scans each file in fileArray and makes a hashtable of the word occurences 
   * 
   * @param: fileArray - an array of files to be processed
   */ 
  public Hashtable<String, Integer> countWordsInMultipleFiles(File[] fileArray){
    Hashtable<String, Integer> table = new Hashtable<String, Integer>();
    System.out.println("countWordsInMultipleFiles" + fileArray.length);
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
  
  
  /* mergeWithMaster()
   * 
   * Merge a thread's local hash table into the master hashtable
   * 
   * @param: table - the local table of the thread that's calling mergeWithMaster
   */ 
  private void mergeWithMaster(Hashtable<String, Integer> table){
    
    Set<String> keys = table.keySet();
    Iterator<String> iter = keys.iterator(); 
    
    while (iter.hasNext()){ //iterate through all the local file's hashtable keys
      
      String word = iter.next();

      if (master.containsKey(word)) {
        master.put(word, table.get(word) + master.get(word)); //if the word is already in the master hashtable, add the total number of occurences
      } else {
        master.put(word, table.get(word)); //if word is not in master, make new entry
      }
    }
    //show(master);
  }
  
  
  
  
  /*show()
   * 
   * Print out the contents of a passed in hashtable to the console
   * 
   * @param: table - the hashtable to be printed
   */ 
  public static void show(Hashtable<String, Integer> table) {
    
    Enumeration<String> entries = table.keys();
    while(entries.hasMoreElements()){
      String next = entries.nextElement(); 
      
      System.out.println(next + ",\t" + table.get(next)); //print in the form: <word>,    <# occurences>
    }
  }
  
  
  /*output()
   * 
   * write the master table to the specified file name in the local directory. This method is called after all 
   * threads have merged with the master.
   * 
   * @param: outputFileName - name of the file to write to
   */ 
  public static void output(String outputFileName){
    File outputFile = new File(outputFileName);
    
    try{
      FileWriter writeOutputFile = new FileWriter(outputFile);
      
      Enumeration<String> entries = master.keys();
      //System.out.println("Enum" + entries.hasMoreElements() );
      
      while(entries.hasMoreElements()){
        String next = entries.nextElement();
        writeOutputFile.write(next + ":\t\t" + master.get(next)+"\n"); //write each line in the form: <word>,    <# occurences>
        System.out.println(next + ":\t\t" + master.get(next)+"\n");
      }
      
      writeOutputFile.close();
    }catch(IOException ex){
      System.err.println(ex);
    }
  }
  
}