/* Hannah Murphy and Alyssa Rivera
 * CS 349a - Assignment 1
 * Febrary 7, 2017
 * 
 * WordCountMultiThread.java
 * 
 * Sets up the Word counter to split up all the files as evenly as possible and run the threads
 */ 

import java.io.*;
import java.util.Enumeration;


public class WordCountMultiThread{
  
  
  //count how many files -
  //determine how many files should be assigned to each thread
  //make s many threads as there are specified in the argument 
  //each thread creates hash table of counted words
  //merge all the hashtables to master hashtable -- thread by thread master key
  //generate output file from master hashtable
  
  /*createFileList()
   * 
   * Creates an array of files that contains the full list of .txt files from the specified directory
   * 
   * @param: directory - the string used to find the directory where the .txt files are stored
   */ 
  public File[] createFileList(String directory) {
    TextFileNameFilter textFilter = new TextFileNameFilter(); //only get .txt files
    File[] files = new File(directory).listFiles(textFilter); //get .txt files from specifed directory
    
    System.out.println(files.length);
    for(int i=0; i < files.length; i++){
      System.out.println(files[i].toString());
      
    }
    return files;
  }
  
  
  /* getAssignmentLength()
   * 
   * Determine how many files to assign to each thread
   */ 
  public int getAssignmentLength(File[] files, int threadCount){
//    if(files.length%threadCount == 0){
//      return files.length/threadCount;
//    }else
    return files.length/threadCount;
  }
  
  
  
  public CounterThread[] assignThreads(File[] files, int threadCount, String outputFileName){
    
    int assignLength = files.length/threadCount; // without a remainder, how many files should be assigned to each thread?
    int fileIndex = 0; //keeps track of the loop's place in the array of files
    CounterThread[] newThreads = new CounterThread[threadCount]; 
    
    if(files.length%threadCount == 0){ //if the files can be divided evenly amongst the threads
      System.out.println("Can be divided evenly");
      for(int i = 0; i < threadCount; i++){
        
        File[] assignedFiles = new File[assignLength]; //fiel array to be passed into CounterThread constructor
        
        for(int j = 0; j < assignLength; j++){
          assignedFiles[j] = files[fileIndex];
          fileIndex++;
        }
        
        newThreads[i] = new CounterThread(assignedFiles, threadCount, outputFileName); // creates counterthread object and places in thread array
      }
      
    }else{ // if the files cannot be divided evenly
       
      System.out.println("Can't be divided evenly");
      System.out.println("File Index: " + fileIndex);
      
      for(int i = 0; i < threadCount-1; i++){ // fill the threads that can be assigned evenly
        System.out.println("Creating Thread: " + i);
        File[] assignedFiles = new File[assignLength];
        
        for(int j = 0; j < assignLength; j++){
          assignedFiles[j] = files[fileIndex];
          fileIndex++;
        }
        newThreads[i] = new CounterThread(assignedFiles, threadCount, outputFileName);
      }
      
      //populates the last thread with the remaining files 
        int filesLeftOver = files.length - fileIndex;
        System.out.println("Number of Files Left Over: " + filesLeftOver);
        
        File[] assignedLeftOverFiles = new File[filesLeftOver];
        for(int j = 0; j < filesLeftOver; j++){ 
          assignedLeftOverFiles[j] = files[fileIndex];
          fileIndex++;
        }
        
        newThreads[newThreads.length-1] = new CounterThread(assignedLeftOverFiles, threadCount, outputFileName); //fill the last thread with the remaining files
        System.out.println("Left over files array length: " + assignedLeftOverFiles.length);
      }
    return newThreads;
  }
  
  
  public static void main (String[] args){
 
    int threadCount = Integer.parseInt(args[0]);
    String outputFileName = args[1];
    
    WordCountMultiThread t1 = new WordCountMultiThread();
    
    File[] files = t1.createFileList("gutenberg-testing");
    
    CounterThread[] threads = t1.assignThreads(files,threadCount, outputFileName);
    
    for(int i = 0; i < threads.length; i++){
      threads[i].start();
    }
  
  }

}
