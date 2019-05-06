import java.util.Random;

public class Noisemaker 
{

	static int chance = 35;

	static Random generator = new Random();
	
	public static int[] randomErrors(int[] bits)
	{
		int key = 1;
		int[] barr = new int[bits.length];
		for(int i=0; i<barr.length; i++)
		{
			if(1 == generator.nextInt(chance)) barr[i] = (int) (key^bits[i]);
			else barr[i] = bits[i];
				
		}
		return barr;
	}
}
