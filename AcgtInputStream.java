/* 
 * AcgtInputStream.java 
 * 
 * Version: 
 *     $Id$
 * 
 * Revisions: Initial Version
 *    
 */
/**
 * AcgtInputStream is a Custom class which implements a read method
 * for uncompressing a compressed file.
 *
 * @author Sanket Agarwal
 * 
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class AcgtInputStream extends InputStream{

	FileInputStream fileInput;
	public AcgtInputStream(FileInputStream fileInput){
		this.fileInput = fileInput;
	}
	static byte prev;
	static byte cur;
	public int read(byte[] newBuf) throws IOException{
		byte[] buffer = new byte[1024];
		fileInput.read(buffer);
		prev = buffer[0];
		cur = buffer[0];
		int temp = 0;
		int dup = 0;

		for(int k:buffer){
			cur = (byte) k;
			if(cur==0){
				break;
			}
			if(cur == 97 || cur == 99 || cur == 103 || cur == 116 || cur == 10){
				newBuf[temp++]=cur;
				//System.out.println("cur :"+cur);
				prev = cur;

			}
			else if(cur == 2|| cur == 3|| cur== 4|| cur == 5|| cur==6 || cur==7){
				for(dup=temp; dup<temp+(int)cur-1; dup++){
					//System.out.println("prev :"+cur+ " "+(char)(cur));
					newBuf[dup]=prev;

				}
				//k++;
				temp = dup;
			}
			else{
				System.out.println("Invalid characters "+" "+cur +" "+ (char)cur);
			}
		}

		if(temp==0){
			return -1;
		}
		return temp;
	}

	@Override
	public int read() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

}
