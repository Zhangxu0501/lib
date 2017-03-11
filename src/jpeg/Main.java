package jpeg;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
public class Main {
	public static void main(String[] args){
		String infile = "C:\\Users\\lining\\Desktop\\Projects\\jpeg\\data\\lena.bmp";
		String outfile = "C:\\Users\\lining\\Desktop\\Projects\\jpeg\\data\\lena.txt";
		BmpReader reader = new BmpReader();
		
		img TheImg = reader.getTheData(infile);
		System.out.println(TheImg.width + " " + TheImg.height);
		
		//int[] outdata = new int[(TheImg.width+1)*(TheImg.height+1)];
		
		int sx,sy;
		int[][] temp = new int[8][8];
		try{
			OutputStream os = new FileOutputStream(outfile);  
	        DataOutputStream out = new DataOutputStream(os);  
			for (int i = 0 ; i<TheImg.height/8+1; i++){
				for (int j = 0; j<TheImg.width/8+1; j++){
					sx = 8*i;
					sy = 8*j;
					for (int p = sx; p<sx+8; p++){
						for (int q = sy; q<sy+8; q++){
							if (p<TheImg.height && q<TheImg.width)
								temp[p-sx][q-sy] = TheImg.data[q+p*TheImg.width];
							else 
								temp[p-sx][q-sy] = 0;//使用0来填充
						}
					}
					JPEG.DCTandSCH(temp);
					for (int p = sx; p<sx+8; p++){
						for (int q = sy; q<sy+8; q++){
							out.write(temp[p-sx][q-sy]); 
						}
					}
				}
			}
			out.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
