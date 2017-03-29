package jpeg;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
public class Main {
	public static void main(String[] args)throws Exception{
		
		int countrm=0;
		int count_rm=0;
		int countsm=0;
		int count_sm=0;
		
		String infile = "/Users/zhangxu/workspace/lib/data/lenna.bmp";
		int bit_len = 5;
		BmpReader reader = new BmpReader();
		
		img TheImg = reader.getTheData(infile);
		System.out.println(TheImg.width + " " + TheImg.height);
		
		
		int sx,sy;
		int[][] temp = new int[8][8];
		int [][] temp1=null;
		int [][]temp2=null;
			for (int i = 0 ; i<TheImg.height/8+1; i++){
				for (int j = 0; j<TheImg.width/8+1; j++){
					   		temp[i%8][j%8]=TheImg.data[i*TheImg.height+j];
				}
				if(i%8==0)
				{
					temp1=f1(temp.clone());
			   		temp2=f_1(temp.clone());
			   		double value=compute(temp);
			   		double value1=compute(temp1);
			   		double value2=compute(temp2);
			   		
			   		if(value<value1)
			   			countrm+=1;
			   		else
			   			countsm+=1;
			   		
			   		if(value<value2)
			   			count_rm+=1;
			   		else
			   			count_sm+=1;
				}
			}
					
			}
	
	public static int [] [] f1(int [] [] temp)
	{
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
			{
				if(Integer.lowestOneBit(temp[i][j])==1)
					temp[i][j]-=1;
			}
		return temp;
	}
	public static int [][] f_1(int [] [] temp)
	{
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
			{
				if(Integer.lowestOneBit(temp[i][j])!=1)
					temp[i][j]-=1;
			}
		return temp;
	}
	public static double compute(int [][] temp)
	{
		return 0.0;
	}
		
	}

