//import java.io.File;
import java.io.*;
import java.util.Enumeration;
public class WordCountMultiThread{
  
  
  //count how many files -
  //determine how many files should be assigned to each thread
  //make s many threads as there are specified in the argument 
  //each thread creates hash table of counted words
  //merge all the hashtables to master hashtable -- thread by thread master key
  //generate output file from master hashtable
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
  
  public int getAssignmentLength(File[] files, int threadCount){
//    if(files.length%threadCount == 0){
//      return files.length/threadCount;
//    }else
    return files.length/threadCount;
  }
  
  public CounterThread[] assignThreads(File[] files, int threadCount){
    //int assignLength = getAssignmentLength(files, threadCount);
    int assignLength = files.length/threadCount;
    int fileIndex = 0;
    CounterThread[] newThreads = new CounterThread[threadCount];
    if(files.length%threadCount == 0){
      System.out.println("Can be divided evenly");
      //int assignLength = files.length/threadCount;
      for(int i = 0; i < threadCount; i++){
        File[] assignedFiles = new File[assignLength];
        for(int j = 0; j < assignLength; j++){
          assignedFiles[j] = files[fileIndex];
          fileIndex++;
          
        }
        newThreads[i] = new CounterThread(assignedFiles, threadCount);
      }
    }else{ // if it cannot be divided evenly
       //fill the ones that can be assignd evenly
      System.out.println("Can't be divided evenly");
      System.out.println("File Index: " + fileIndex);
      //int filesLeft = files.length - fileIndex;
      for(int i = 0; i < threadCount-1; i++){
        System.out.println("Creating Thread: " + i);
        File[] assignedFiles = new File[assignLength];
        for(int j = 0; j < assignLength; j++){
          assignedFiles[j] = files[fileIndex];
          fileIndex++;
        }
        newThreads[i] = new CounterThread(assignedFiles, threadCount);
      }
      
      //takes care of the left over
        int filesLeftOver = files.length - fileIndex;
        System.out.println("Number of Files Left Over: " + filesLeftOver);
        File[] assignedLeftOverFiles = new File[filesLeftOver];
        for(int j = 0; j < filesLeftOver; j++){
          assignedLeftOverFiles[j] = files[fileIndex];
          fileIndex++;

        }
        newThreads[newThreads.length-1] = new CounterThread(assignedLeftOverFiles, threadCount);
        System.out.println("Left over files array length: " + assignedLeftOverFiles.length);
      }
    return newThreads;
  }
  
  public void output(String outputFileName){
    
  }
//  public void writeOutput(String outputFileName){
//    File outputFile = new File(outputFileName);
//    try{
//      FileWriter writeOutputFile = new FileWriter(outputFile);
//      
//      Enumeration<String> entries = table.keys();
//      while(entries.hasMoreElements()){
//        String next = entries.nextElement();
//        
//        writeOutputFile.write(next + ":\t\t" + table.get(next)+"\n");
//      }
//      writeOutputFile.close();
//    }catch(IOException ex){
//      System.err.println(ex);
//    }
//  }
//  
  public static void main (String[] args){
    //get file array
    WordCountMultiThread t1 = new WordCountMultiThread();
    int threadCount = 6;
    File[] files = t1.createFileList("test-text");
    CounterThread[] threads = t1.assignThreads(files,threadCount);
    for(int i = 0; i < threads.length; i++){
      threads[i].start();
    }
    
    //t1.output("output.txt");
    //File a = new File("test-text/test1.txt");
    //File b = new File("test-text/test2.txt");
    //File c = new File("test-text/test3.txt");
    
    //CounterThread c1 = new CounterThread(a);
    //CounterThread c2 = new CounterThread(b);
    //CounterThread c3 = new CounterThread(c);
    
    
    //count how many files
    //make that many threads
    //each thread creates hash table of counted words
    //merge all the hashtables to master hashtable -- thread by thread master key
    //generate output file from master hashtable
  
  }

}
