/* 
 * CustomZipUnzip.java 
 * 
 * Version: 
 *     $Id$
 * 
 * Revisions: Initial Version
 *    
 */
/**
 * CustomZipUnzip is a Custom class which compresses and 
 * uncompresses file containing only letters a,c,t & g...
 *
 * @author Sanket Agarwal
 * 
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.*;
public class CustomZipUnzip {
	public static void compress_file(String filename){

		try {
			FileInputStream inputStream = new FileInputStream(filename);
			AcgtOutputStream outputStream = new AcgtOutputStream(new FileOutputStream(filename +  ".Z"));
			byte[] buffer = new byte[1026];
			int buffer_len=0;
			int len;

			while((len = inputStream.read(buffer)) != -1){
				outputStream.write(buffer, buffer_len, len);

			}
			inputStream.close();
			outputStream.close();

		}
		catch ( FileNotFoundException ef)	{
			System.out.println(ef.getMessage() );
		}
		catch ( IOException ef)	{
			System.out.println(ef.getMessage() );
		}
		catch ( Exception e)	{
			System.out.println("ExceptionType occurred: " + 
					e.getMessage() );
		}

	}

	public static void Uncompress_file(String filename){
		try {
			FileOutputStream outputStream = new FileOutputStream(filename +  ".uc");
			AcgtInputStream gzinput = new AcgtInputStream(new FileInputStream(filename));
			byte[] buffer = new byte[1024*2];
			int len;

			while((len = gzinput.read(buffer)) !=-1){
				outputStream.write(buffer, 0, len);
			}

			outputStream.close();
			gzinput.close();

		}
		catch ( FileNotFoundException ef)	{
			System.out.println(ef.getMessage() );
		}
		catch ( IOException ef)	{
			System.out.println(ef.getMessage() );
		}
		catch ( Exception e)	{
			System.out.println("ExceptionType occurred: " + 
					e.getMessage() );
		}

	}


	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		if ( args.length != 1 )	{
			System.err.println(
					"Usage: java InOut_1 from to [size]");

			System.exit(1);
		}
		String pattern=".*\\.txt";
		String pattern1=".*\\.txt.Z";
		Pattern p=Pattern.compile(pattern);
		Pattern p1=Pattern.compile(pattern1);
		Matcher m=p.matcher(args[0]);
		Matcher m1=p1.matcher(args[0]);

		if(m.matches()){			
			compress_file(args[0]);
			System.out.println("Succesfully compressed");
		}
		else if(m1.matches()){
			Uncompress_file(args[0]);
			System.out.println("Succesfully uncompressed");

		}
		else{
			System.out.println("Enter proper file name");
		}
		sc.close();

	}

}
