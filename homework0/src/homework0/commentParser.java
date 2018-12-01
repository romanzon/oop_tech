package homework0;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import java.nio.file.Files;

/**
 * commentParse represents a class that prints all comments appeared in java program
 **/

public class commentParser {
	
	/**
	 * @effects Runs the main program of commentParser
	 **/
    public static void main(String args[])  
    {  
    	if (args.length == 0)
    		throw new AssertionError("User must provide a comment file path");
    	parser(args[0]);
    }  

	/**
	 * @effects Print all comments appeared in java program
	 **/
    public static void parser(String filePathStr)
    {
    	Path path = Paths.get(filePathStr);
    	if (!Files.exists(path))
    		throw new AssertionError("File path doesn't exist");

		try {
			BufferedReader read_file;
			read_file = new BufferedReader(new FileReader(filePathStr));
			
			boolean is_multi_comment = false;
			String line = read_file.readLine(); // Read the first line
			while (line != null)
	    	{
	    		StringTokenizer st = new StringTokenizer(line); // Break the line into word in order to find comment prefix
	    		boolean is_comment = false;
	    		
	    		String commentStr = null;
	    		while (st.hasMoreTokens()) {
	    			String word = st.nextToken();
	    			
	    			if (word.endsWith("*/")) // Check if the line is the last mutli-line comment
	    			{
	    				if (commentStr == null)
	    					commentStr = word;
	    				else
	    					commentStr += " " + word;
	    				is_multi_comment = false;
	    			}
	    			else if (is_multi_comment) // Accumulate the comment into comment line
	    			{
	    				if (commentStr == null)
	    					commentStr = word;
	    				else
	    					commentStr += " " + word;
	    			}
	    			else if (is_comment) // Accumulate the comment into comment line
	    			{
	    				commentStr += " " + word;
	    			}
	    			else if (word.startsWith("/*")) // Check if the line is the first multi-line comment
	    			{
	    				is_multi_comment = true;
	    				commentStr = word;
	    			}
	    			else if (word.startsWith("//")) // Check if the line contains comment
	    			{
	    				is_comment = true;
	    				commentStr = word;
	    			}
	    		}
	    		if (commentStr != null) // Print the comment line
	    			System.out.println(commentStr);
	    		line = read_file.readLine();
	    	}
	    	read_file.close();
	    	
		} catch (IOException e) {
			e.printStackTrace();
		}    	
    }
}