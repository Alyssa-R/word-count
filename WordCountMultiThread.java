import java.io.File;

public class WordCountMultiThread{
  
  public static void main (String[] args){
  
    File a = new File("test-text/test1.txt");
    File b = new File("test-text/test2.txt");
    File c = new File("test-text/test3.txt");
    
    CounterThread c1 = new CounterThread(a);
    CounterThread c2 = new CounterThread(b);
    CounterThread c3 = new CounterThread(c);
    
    c1.start();

    c2.start();
    c3.start();
    
    
    CounterThread.output("output.txt");
    
    //count how many files
    //make that many threads
    //each thread creates hash table of counted words
    //merge all the hashtables to master hashtable -- thread by thread master key
    //generate output file from master hashtable
  
  }

}