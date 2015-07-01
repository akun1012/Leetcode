import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class WekaOutput {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File here = new File(".");
		System.out.println(here.getAbsolutePath());
		BufferedReader br = new BufferedReader(new FileReader("output.txt"));
		PrintWriter writer = null;
		writer = new PrintWriter( new FileWriter("result2300.txt"));
		String everything = "";
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
//	            sb.append(line);
//	            sb.append(System.lineSeparator());
//	            line = br.readLine();
	        	String[] temp = line.trim().split(" ");
	        	String num = "";
				try {
					num = temp[18].substring(0, 1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(line);
					e.printStackTrace();
				}
//writer = new PrintWriter( new FileWriter("result.txt"));
				writer.println(num);
				line = br.readLine();
	        	
	        }
//	        everything = sb.toString();
	    } finally {
	        br.close();
	        if ( writer != null)
		        writer.close( );
	    }
	    
	    System.out.println("Done");
	    }


//		try
//		{
//		    writer = new BufferedWriter( new FileWriter("result.txt"));
//		    writer.write( everything);
//	
//		}
//		catch ( IOException e)
//		{
//		}
//		finally
//		{
//		    try
//		    {
//		        if ( writer != null)
//		        writer.close( );
//		    }
//		    catch ( IOException e)
//		    {
//		    }
//		}
//	
		

}
