package jpeg;

import java.util.Arrays;

public class Main {
	public static void sop(Object o)
	{
		System.out.println(o);
	}
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
			for (int i = 0 ; i<TheImg.width; i++){
				for (int j= 0 ; j<TheImg.height; j++){
					if(i%8==0&&j%8==0)
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
				   		sop(value);
				   		sop(value1);
				   		sop(value2);
				   		print(temp);
				   		sop("----------------------");
				   		print(temp1);
				   		sop("----------------------");
				   		print(temp2);
				   		System.in.read();
				}
					   		temp[i%8][j%8]=TheImg.data[i*TheImg.height+j];
					   		
			}
				
			}
			sop("countrm="+countrm);
			sop("count_rm="+count_rm);
			sop("countsm="+countsm);
			sop("count_sm="+count_sm);
			}
	
	public static int [] [] f1(int [] [] temp)
	{
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
			{
				if(Integer.lowestOneBit(temp[i][j])==1)
				{
					sop("-1-1");
					temp[i][j]=temp[i][j]-1;
				}
				temp[i][j]=0;
					
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
		int curi=1,curj=0,lasti=0,lastj=0;
		int ilen=temp.length,jlen=temp[0].length;
//		System.out.printf("ilen=%d,jlen=%d\n",ilen,jlen);
		int direction=1;//-1 down  1 up 0 end
		double res=0.0;
		
		while(true)
		{
			res += Math.abs(temp[curi][curj]-temp[lasti][lastj]);
//			System.out.printf("(%d,%d),dir=%d\n",curi,curj,direction);
			if(curi==ilen-1 && curj==jlen-1)break;
			lasti = curi;lastj=curj;
			curi += -direction;
			curj += direction;
			if(curi >=0 && curi<ilen && curj>=0 && curj<jlen)continue;
		
			direction=-direction;
			if(curi==-1){if(curj==jlen){curi+=2;curj-=1;}else{curi+=1;}}
			else if(curi==ilen){curi-=1;if(curj<jlen-2)curj+=2;}
			else if(curj==-1){curj+=1;}
			else if(curj==jlen){if(curi<ilen-2)curi+=2;curj-=1;}
			
		}
		return res;
	}
	public static void  print(int [] [] temp)
	{
		for(int i=0;i<temp.length;i++)
		System.out.println(Arrays.toString(temp[i]));
	}
		
	}

