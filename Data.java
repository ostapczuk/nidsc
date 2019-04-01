import java.lang.reflect.Array;
import java.util.Random;


public class Data {
	Random radom = new Random(System.currentTimeMillis());
	public int[] bits;
	
	public Data(int size){
		//bits = new int[size];
		bits = (int[]) Array.newInstance(int.class, size);
		for(int i = 0; i < size; i++) {
			bits[i] = Math.abs(radom.nextInt()%2);
			//System.out.println(radom.nextInt());
		}
		
	}

}
