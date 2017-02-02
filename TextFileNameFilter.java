import java.io.FilenameFilter;
import java.io.File;

public class TextFileNameFilter implements FilenameFilter{
  
  public boolean accept(File dir, String name){
    
    System.out.println(name.substring(name.length()-4, name.length()));
    
      if(name.substring(name.length()-4,name.length()).equals(".txt")){
      return true;
    }
    return false;
  }
}