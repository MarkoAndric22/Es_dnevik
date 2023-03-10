package Es_dnevniks.exception;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.springframework.stereotype.Component;

public class FileErrors {
	  
	  public static void appendToFile(String e) {
	        try {
	            FileWriter file = new FileWriter("errors.txt",true);
	            BufferedWriter buff_file = new BufferedWriter(file);
	            buff_file.write(e);
	            buff_file.newLine();
	            buff_file.close();
	        }
	        catch (Exception ie) {
	            throw new RuntimeException("Cannot write the Exception to file", ie);
	        }
	   }

}
