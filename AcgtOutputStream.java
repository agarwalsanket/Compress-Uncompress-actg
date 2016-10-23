/* 
 * AcgtOutputStream.java 
 * 
 * Version: 
 *     $Id$
 * 
 * Revisions: Initial Version
 *    
 */
/**
 * AcgtOutputStream is a Custom class which implements a write method
 * for compressing a  file containing only letters a,c,t & g..
 *
 * @author Sanket Agarwal
 * 
 */

import java.io.OutputStream;
//import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class AcgtOutputStream extends  OutputStream{

	FileOutputStream fileOut;
	public  AcgtOutputStream(FileOutputStream fileOut){
		this.fileOut = fileOut;


	}
	@Override
	public void write(int b) throws IOException {


	}
	public void write(byte[] buf,
			int off,
			int len)
					throws IOException{

		//ByteArrayOutputStream b_array = new ByteArrayOutputStream(len);

		int i = 0;
		byte cur = 0;
		byte prev = buf[0];
		byte count = 0;
		for(int m=0; m<len; m++){

			cur = buf[i++];
			if(cur == prev){				 
				count++;
				prev = cur;
				if(m == len-1){
					fileOut.write((char)cur);
					fileOut.write(count);	
				}
			}
			else{
				char temp1 = (char) prev;
				//b_array.write(temp1);	
				fileOut.write(temp1);

				if(count!=1){
					//b_array.write((byte)count); 
					fileOut.write(count);
				}
				if(m == len-1){
					fileOut.write((char)cur);	
				}


				prev = cur;
				count = 1;		 
			}

		}


	}

}
