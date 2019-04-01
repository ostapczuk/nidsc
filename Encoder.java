
public class Encoder {
	
	public static int[] repetition(int[] input, int rep) { // tylko liczby nieparzyste jako liczba powtórzeń!
		int[] output = new int[rep*input.length];
		for(int i = 0; i < input.length; i++) {
			for(int j = 0; j < rep; j++) {
				output[rep*i+j] = input[i];
			}
		}
		
		return output;
	}

	public static int[] CRC(int[] input) {
		int[] output = new int[input.length];
		for(int i = 0; i < input.length; i++) {
			
		}
		
		return output;
	}

}
