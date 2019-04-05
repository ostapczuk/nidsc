
public class Decoder {
	
	public static int[] repetition(int[] input, int rep) { //TYLKO DLA NJEPARŻYSTYH
		int[] output = new int[input.length/rep];
		for(int i = 0; i < output.length; i++) {
			int a0 = 0;
			int a1 = 0;
			for(int j = 0; j < rep; j++) {
				if (input[rep*i+j] == 0) a0++;
				else a1++;
			}
			if(a0 > a1) output[i] = 0;
			else output[i] = 1;
		}
		
		return output;
	}

	public static int[] Hamming74(int[] transferdata) {
		// TODO Auto-generated method stub
		return transferdata;
	}

}
