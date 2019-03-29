import java.util.Random;

public class ByteOperations 
{
	int chance = 1000000;
	Random generator = new Random();
	
	public byte[] randomErrors(ByteStream bytes)
	{
		byte key = 1;
		byte[] barr = new byte[bytes.byteArray.length];
		for(int i=0; i<barr.length; i++)
		{
			if(1 == generator.nextInt(chance)) barr[i] = (byte) (key^bytes.byteArray[i]);
			else barr[i] = bytes.byteArray[i];
				
		}
		return barr;
	}
}
