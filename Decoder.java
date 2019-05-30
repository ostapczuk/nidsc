
public class Decoder {
	
	static int HammingH[][] = { // macierz parzystosci [3][7]
			{1,0,1,0,1,0,1},
			{0,1,1,0,0,1,1},
			{0,0,0,1,1,1,1}
	};
	
	/*
	static int HammingR[][] = { // macierz dekodowania [4][7]
			{0,0,1,0,0,0,0},
			{0,0,0,0,1,0,0},
			{0,0,0,0,0,1,0},
			{0,0,0,0,0,0,1}
	};
	*/
	
	public static int[] repetition(int[] input, int rep, Counter count, int[] source) 
	{ //TYLKO DLA NJEPARŻYSTYH
		int[] output = new int[input.length/rep];
		boolean[] errors = new boolean[input.length/rep];
		for(int i = 0; i < output.length; i++) 
		{
			int a0 = 0;
			int a1 = 0;
			for(int j = 0; j < rep; j++) 
			{
				if (input[rep*i+j] == 0) a0++;
				else a1++;
			}
			if(a1 != rep && a0 != rep) errors[i] = true;
			else errors[i] = false;
			if(a0 > a1) output[i] = 0;
			else output[i] = 1;
		}
		count.count(source, output, errors);
		return output;
	}

	public static int[] Hamming74(int[] transferdata, Counter count, int[] source) 
	{
		int[] output = new int[4*transferdata.length/7];
		boolean[] errors = new boolean[4*transferdata.length/7];
		int [] syndrome = new int[3];
		for(int i = 0; i < transferdata.length; i+=7) 
		{ // bierzemy 7 bitów, korygujemy i oddajemy 4
			for(int j = 0; j < 3; j++) 
			{
				for (int k = 0; k < 7; k++) 
				{
					syndrome[j] += HammingH[j][k] * transferdata[i+k];
				}
			}
			for (int j = 0; j < syndrome.length; j++) 
			{
				syndrome[j] = syndrome[j]%2;
			}
			int bitError = 4 * syndrome[2] + 2 * syndrome[1] + syndrome[0];
			if(bitError!=0)
			{
				transferdata[i + bitError-1]++;
				transferdata[i + bitError-1] = transferdata[i + bitError-1]%2;
				if(syndrome[0] == 0 && syndrome[1] == 1 && syndrome[2] == 1) errors[4*i/7] = true;
				else errors[4*i/7] = false;
				if(syndrome[0] == 1 && syndrome[1] == 0 && syndrome[2] == 1) errors[4*i/7+1] = true;
				else errors[4*i/7+1] = false;
				if(syndrome[0] == 1 && syndrome[1] == 1 && syndrome[2] == 0) errors[4*i/7+2] = true;
				else errors[4*i/7+2] = false;
				if(syndrome[0] == 1 && syndrome[1] == 1 && syndrome[2] == 1) errors[4*i/7+3] = true;
				else errors[4*i/7+3] = false;
			}
			else
			{
				errors[4*i/7] = false;
				errors[4*i/7+1] = false;
				errors[4*i/7+2] = false;
				errors[4*i/7+3] = false;
			}
			output[4*i/7] = transferdata[i+2];
			output[4*i/7+1] = transferdata[i+4];
			output[4*i/7+2] = transferdata[i+5];
			output[4*i/7+3] = transferdata[i+6];
		}
		count.count(source, output, errors);
		return output;
	}

	public static int[] Hamming74(int[] transferdata) {
		int[] output = new int[4*transferdata.length/7];
		int [] syndrome = new int[3];
		for(int i = 0; i < transferdata.length; i+=7) { // bierzemy 7 bitów, korygujemy i oddajemy 4
			for(int j = 0; j < 3; j++) {
				for (int k = 0; k < 7; k++) {
					syndrome[j] += HammingH[j][k] * transferdata[i+k];
				}
			}
			for (int j = 0; j < syndrome.length; j++) {
				syndrome[j] = syndrome[j]%2;
			}
			int bitError = 4 * syndrome[2] + 2 * syndrome[1] + syndrome[0];
			if(bitError!=0){
				transferdata[i + bitError-1]++;
				transferdata[i + bitError-1] = transferdata[i + bitError-1]%2;
			}
			output[4*i/7] = transferdata[i+2];
			output[4*i/7+1] = transferdata[i+4];
			output[4*i/7+2] = transferdata[i+5];
			output[4*i/7+3] = transferdata[i+6];
		}
		
		return output;
	}

}
