/* Hannah Murphy and Alyssa Rivera
 * CS 349a - Assignment 1
 * Febrary 7, 2017
 * 
 * TextFileNameFilter.java
 * 
 * Filters out filenames within a directory so only .txt files are kept. Implements FileNameFilter interface.
 */ 


import java.io.FilenameFilter;
import java.io.File;

public class TextFileNameFilter implements FilenameFilter{
  
  public boolean accept(File dir, String name){
    
    //System.out.println(name.substring(name.length()-4, name.length()));
    
      if(name.substring(name.length()-4,name.length()).equals(".txt")){ //if it's a .txt file, return true
        
      return true;
    }
    return false; // if not .txt, return false
  }
}