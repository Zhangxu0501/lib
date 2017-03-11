package jpeg;
public class JPEG 
{	
	public static short Round(double x)
	{
		if(x>=0.0)
			return (short)(x+0.5);
		else
			return (short)(x-0.5);
	}
	
	public static void DCT(int [][]n)
	{
		double u,v;
		double Pi=3.141592653;
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				if(i==0)
					u = Math.sqrt(1/8);
				else u = Math.sqrt(2/8);
				if(j==0)
					v = Math.sqrt(1/8);
				else v = Math.sqrt(2/8);
				double temp=0.0;
				for(int x=0;x<8;x++)
					for(int y=0;y<8;y++)
						temp += n[x][y]*Math.cos((x+0.5)*Pi*i/8)*Math.cos((y+0.5)*Pi*j/8);
				n[i][j] = (int) Round(u*v*temp);							
			}
		}
		return;
	}
	
	static int Bq[][] = {{16,11,10,16,24,40,51,61},
						 {12,12,14,19,26,58,60,55},
						 {14,13,16,24,40,57,69,56},
						 {14,17,22,29,51,87,80,62},
						 {18,22,37,56,68,109,103,77},
						 {24,35,55,64,81,104,113,92},
						 {49,64,78,87,103,121,120,101},
						 {72,92,95,98,112,100,103,99}
						};
	
	public static void SCH(int [][]n)
	{
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
			{
				n[i][j] = (int)(n[i][j]/Bq[i][j]);
			}
		return;
	}
	
	public static void main(String args[])
	{
		int[][] n = new int[8][8];
		
		System.out.println("before :");
		for (int i = 0 ;i<8;i++){
			for (int j = 0; j<8; j++){
				n[i][j] = j*2+i*10;
				System.out.print(n[i][j]);
			}
			System.out.println();
		}
		
		System.out.println("after dct:");
		DCT(n);
		for(int i = 0 ; i< 8 ;i++){
			for(int j = 0; j<8 ; j++){
				System.out.print(n[i][j]);
			}
			System.out.println();
		}

		System.out.println("after sch:");
		SCH(n);
		for (int i = 0 ;i<8;i++){
			for (int j = 0; j<8; j++){
				System.out.print(n[i][j]);
			}
			System.out.println();
		}
		

	}

}
