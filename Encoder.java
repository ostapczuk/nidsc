public class Encoder {
	static int HammingG[][] = { // macierz generujaca [7][4]
			{1,1,0,1},
			{1,0,1,1},
			{1,0,0,0},
			{0,1,1,1},
			{0,1,0,0},
			{0,0,1,0},
			{0,0,0,1}
	};
	
	
	
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

	public static int[] Hamming74(int[] bits) {
		int[] output = new int[7 * (bits.length/4)];
		// Kod cykliczny Hamminga (7,4)
		
		for(int i = 0; i < bits.length; i+=4) {
			//dzielenie na pakiety 4-bitowe z 3-bit nadmiarem
			for(int j = 0; j < 4; j++) { // 4-bitowy wektor p
				for(int k = 0; k < 7; k++) {
					output[7*i/4 + k] += HammingG[k][j]*bits[i + j]; // mnozenie macierzowe G*p
				}
			}
		}
		for (int i = 0; i < output.length; i++) {
			output[i] = output[i]%2;
		}
		
		return output;
	}

}
