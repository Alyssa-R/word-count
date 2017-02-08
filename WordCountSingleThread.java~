/* Hannah Murphy and Alyssa Rivera
 * CS 349a - Assignment 1
 * Febrary 7, 2017
 * 
 * WordCountSingleThread.java
 * 
 * Counts the total occurences of a word in a directory of files
 */

import java.util.*;
import java.io.*;

public class WordCountSingleThread {
  
  private Hashtable<String, Integer> table = new Hashtable<String, Integer>();
  
  /*createFileList()
   * 
   * Creates an array of files that contains the full list of .txt files from the specified directory
   * 
   * @param: directory - the string used to find the directory where the .txt files are stored
   */ 
  public File[] createFileList(String directory) {
    TextFileNameFilter textFilter = new TextFileNameFilter();
    File[] files = new File(directory).listFiles(textFilter);
    //File[] fileList = files.listFiles(textFilter);
    
    System.out.println(files.length);
    for(int i=0; i < files.length; i++){
      System.out.println(files[i].toString());
      
    }
    return files;
  }
  
  /*getDirectoryLength()
   * 
   * Gets the full length of the directory to be processed. 
   * 
   * @param: files - a file array to be counted
   */
  public int getDirectoryLength(File[] files){
    return files.length;
  }
  
  
  /*countWordsInFile()
   * 
   * Scans each file in fileArray and makes a hashtable of the word occurences 
   * 
   * @param: file - a file to be processed
   */ 
  public void countWordsInFile(File file){
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
  
  public void output(String outputFileName){
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
  
  
  public static void main(String args[]) { //supply text folder name as first arg and output file name as second arg
    
    WordCountSingleThread abc = new WordCountSingleThread();
    
  
    File[] files = abc.createFileList("test-text");//args[0]);//"test-text" //make args[0]
    for(File file : files){
      abc.countWordsInFile(file);
    }
    abc.output("output.txt");//args[1]);//"output.txt"
    abc.show();
  }
}