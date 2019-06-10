
public class Decoder {
	
	static int HammingH_7_4[][] = { // macierz parzystosci [3][7]
			{1,0,1,0,1,0,1},
			{0,1,1,0,0,1,1},
			{0,0,0,1,1,1,1}
	};
	
	static int HammingH_15_11[][] = { // macierz parzystosci [4][15]
			{1,0,0,0,1,0,0,1,1,0,1,0,1,1,1},
			{0,1,0,0,1,1,0,1,0,1,1,1,1,0,0},
			{0,0,1,0,0,1,1,0,1,0,1,1,1,1,0},
			{0,0,0,1,0,0,1,1,0,1,0,1,1,1,1}
	};
	
	static int HammingH_31_26[][] = { // macierz parzystosci [5][31]
			{1,0,0,0,0,1,0,0,1,0,1,1,0,0,1,1,1,1,1,0,0,0,1,1,0,1,1,1,0,1,0},
			{0,1,0,0,0,0,1,0,0,1,0,1,1,0,0,1,1,1,1,1,0,0,0,1,1,0,1,1,1,0,1},
			{0,0,1,0,0,1,0,1,1,0,0,1,1,1,1,1,0,0,0,1,1,0,1,1,1,0,1,0,1,0,0},
			{0,0,0,1,0,0,1,0,1,1,0,0,1,1,1,1,1,0,0,0,1,1,0,1,1,1,0,1,0,1,0},
			{0,0,0,0,1,0,0,1,0,1,1,0,0,1,1,1,1,1,0,0,0,1,1,0,1,1,1,0,1,0,1}
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

	public static int[] Hamming_7_4(int[] transferdata, Counter count, int[] source) 
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
					syndrome[j] += HammingH_7_4[j][k] * transferdata[i+k];
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
	
	public static int[] Hamming_15_11(int[] transferdata, Counter count, int[] source)
	{
		int[] output = new int[11*transferdata.length/15];
		boolean[] errors = new boolean[11*transferdata.length/15];
		int[] syndrome = new int[4];
		for (int i = 0; i < transferdata.length; i+=15)
		{
			for (int j = 0; j < 4; j++)
			{
				for (int k = 0; k < 15; k++)
				{
					syndrome[j] += HammingH_15_11[j][k] * transferdata[i+k];
				}
			}
			for (int j = 0; j < syndrome.length; j++)
			{
				syndrome[j] = syndrome[j] % 2;
			}
			int bitError = 8 * syndrome[3] + 4 * syndrome[2] + 2 * syndrome[1] + syndrome[0];
			if (bitError != 0)
			{
				transferdata[i + bitError-1]++;
				transferdata[i + bitError-1] = transferdata[i + bitError-1]%2;
				if(syndrome[0] == 0 && syndrome[1] == 0 && syndrome[2] == 1 && syndrome[3] == 1) errors[11*i/15] = true;
				else errors[11*i/15] = false;
				if(syndrome[0] == 0 && syndrome[1] == 1 && syndrome[2] == 0 && syndrome[3] == 1) errors[11*i/15+1] = true;
				else errors[11*i/15+1] = false;
				if(syndrome[0] == 0 && syndrome[1] == 1 && syndrome[2] == 1 && syndrome[3] == 0) errors[11*i/15+2] = true;
				else errors[11*i/15+2] = false;
				if(syndrome[0] == 0 && syndrome[1] == 1 && syndrome[2] == 1 && syndrome[3] == 1) errors[11*i/15+3] = true;
				else errors[11*i/15+3] = false;
				if(syndrome[0] == 1 && syndrome[1] == 0 && syndrome[2] == 0 && syndrome[3] == 1) errors[11*i/15+4] = true;
				else errors[11*i/15+4] = false;
				if(syndrome[0] == 1 && syndrome[1] == 0 && syndrome[2] == 1 && syndrome[3] == 0) errors[11*i/15+5] = true;
				else errors[11*i/15+5] = false;
				if(syndrome[0] == 1 && syndrome[1] == 0 && syndrome[2] == 1 && syndrome[3] == 1) errors[11*i/15+6] = true;
				else errors[11*i/15+6] = false;
				if(syndrome[0] == 1 && syndrome[1] == 1 && syndrome[2] == 0 && syndrome[3] == 0) errors[11*i/15+7] = true;
				else errors[11*i/15+7] = false;
				if(syndrome[0] == 1 && syndrome[1] == 1 && syndrome[2] == 0 && syndrome[3] == 1) errors[11*i/15+8] = true;
				else errors[11*i/15+8] = false;
				if(syndrome[0] == 1 && syndrome[1] == 1 && syndrome[2] == 1 && syndrome[3] == 0) errors[11*i/15+9] = true;
				else errors[11*i/15+9] = false;
				if(syndrome[0] == 1 && syndrome[1] == 1 && syndrome[2] == 1 && syndrome[3] == 1) errors[11*i/15+10] = true;
				else errors[11*i/15+10] = false;
			}
			else
			{
				errors[11*i/15] = false;
				errors[11*i/15+1] = false;
				errors[11*i/15+2] = false;
				errors[11*i/15+3] = false;
				errors[11*i/15+4] = false;
				errors[11*i/15+5] = false;
				errors[11*i/15+6] = false;
				errors[11*i/15+7] = false;
				errors[11*i/15+8] = false;
				errors[11*i/15+9] = false;
				errors[11*i/15+10] = false;
			}
			output[11*i/15] = transferdata[i+2];
			output[11*i/15+1] = transferdata[i+4];
			output[11*i/15+2] = transferdata[i+5];
			output[11*i/15+3] = transferdata[i+6];
			output[11*i/15+4] = transferdata[i+8];
			output[11*i/15+5] = transferdata[i+9];
			output[11*i/15+6] = transferdata[i+10];
			output[11*i/15+7] = transferdata[i+11];
			output[11*i/15+8] = transferdata[i+12];
			output[11*i/15+9] = transferdata[i+13];
			output[11*i/15+10] = transferdata[i+14];
		}
		count.count(source, output, errors);
		return output;
	}
	
	public static int[] Hamming_31_26(int[] transferdata, Counter count, int[] source)
	{
		int[] output = new int[26*transferdata.length/31];
		boolean[] errors = new boolean[26*transferdata.length/31];
		int[] syndrome = new int[5];
		for (int i = 0; i < transferdata.length; i+=31)
		{
			for (int j = 0; j < 5; j++)
			{
				for (int k = 0; k < 31; k++)
				{
					syndrome[j] += HammingH_31_26[j][k] * transferdata[i+k];
				}
			}
			for (int j = 0; j < syndrome.length; j++)
			{
				syndrome[j] = syndrome[j] % 2;
			}
			int bitError = 16 * syndrome[4] + 8 * syndrome[3] + 4 * syndrome[2] + 2 * syndrome[1] + syndrome[0];
			if (bitError != 0)
			{
				if(syndrome[0] == 0 && syndrome[1] == 0 && syndrome[2] == 0 && syndrome[3] == 1 && syndrome[4] == 1) errors[26*i/31] = true;
				else errors[26*i/31] = false;
				if(syndrome[0] == 0 && syndrome[1] == 0 && syndrome[2] == 1 && syndrome[3] == 0 && syndrome[4] == 1) errors[26*i/31+1] = true;
				else errors[26*i/31+1] = false;
				if(syndrome[0] == 0 && syndrome[1] == 0 && syndrome[2] == 1 && syndrome[3] == 1 && syndrome[4] == 0) errors[26*i/31+2] = true;
				else errors[26*i/31+2] = false;
				if(syndrome[0] == 0 && syndrome[1] == 0 && syndrome[2] == 1 && syndrome[3] == 1 && syndrome[4] == 1) errors[26*i/31+3] = true;
				else errors[26*i/31+3] = false;
				if(syndrome[0] == 0 && syndrome[1] == 1 && syndrome[2] == 0 && syndrome[3] == 0 && syndrome[4] == 1) errors[26*i/31+4] = true;
				else errors[26*i/31+4] = false;
				if(syndrome[0] == 0 && syndrome[1] == 1 && syndrome[2] == 0 && syndrome[3] == 1 && syndrome[4] == 0) errors[26*i/31+5] = true;
				else errors[26*i/31+5] = false;
				if(syndrome[0] == 0 && syndrome[1] == 1 && syndrome[2] == 0 && syndrome[3] == 1 && syndrome[4] == 1) errors[26*i/31+6] = true;
				else errors[26*i/31+6] = false;
				if(syndrome[0] == 0 && syndrome[1] == 1 && syndrome[2] == 1 && syndrome[3] == 0 && syndrome[4] == 0) errors[26*i/31+7] = true;
				else errors[26*i/31+7] = false;
				if(syndrome[0] == 0 && syndrome[1] == 1 && syndrome[2] == 1 && syndrome[3] == 0 && syndrome[4] == 1) errors[26*i/31+8] = true;
				else errors[26*i/31+8] = false;
				if(syndrome[0] == 0 && syndrome[1] == 1 && syndrome[2] == 1 && syndrome[3] == 1 && syndrome[4] == 0) errors[26*i/31+9] = true;
				else errors[26*i/31+9] = false;
				
				if(syndrome[0] == 0 && syndrome[1] == 1 && syndrome[2] == 1 && syndrome[3] == 1 && syndrome[4] == 1) errors[26*i/31+10] = true;
				else errors[26*i/31+10] = false;
				if(syndrome[0] == 1 && syndrome[1] == 0 && syndrome[2] == 0 && syndrome[3] == 0 && syndrome[4] == 1) errors[26*i/31+11] = true;
				else errors[26*i/31+11] = false;
				if(syndrome[0] == 1 && syndrome[1] == 0 && syndrome[2] == 0 && syndrome[3] == 1 && syndrome[4] == 0) errors[26*i/31+12] = true;
				else errors[26*i/31+12] = false;
				if(syndrome[0] == 1 && syndrome[1] == 0 && syndrome[2] == 0 && syndrome[3] == 1 && syndrome[4] == 1) errors[26*i/31+13] = true;
				else errors[26*i/31+13] = false;
				if(syndrome[0] == 1 && syndrome[1] == 0 && syndrome[2] == 0 && syndrome[3] == 1 && syndrome[4] == 0) errors[26*i/31+14] = true;
				else errors[26*i/31+14] = false;
				if(syndrome[0] == 1 && syndrome[1] == 0 && syndrome[2] == 1 && syndrome[3] == 0 && syndrome[4] == 1) errors[26*i/31+15] = true;
				else errors[26*i/31+15] = false;
				if(syndrome[0] == 1 && syndrome[1] == 0 && syndrome[2] == 1 && syndrome[3] == 1 && syndrome[4] == 0) errors[26*i/31+16] = true;
				else errors[26*i/31+16] = false;
				if(syndrome[0] == 1 && syndrome[1] == 0 && syndrome[2] == 1 && syndrome[3] == 1 && syndrome[4] == 1) errors[26*i/31+17] = true;
				else errors[26*i/31+17] = false;
				if(syndrome[0] == 1 && syndrome[1] == 1 && syndrome[2] == 0 && syndrome[3] == 0 && syndrome[4] == 0) errors[26*i/31+18] = true;
				else errors[26*i/31+18] = false;
				if(syndrome[0] == 1 && syndrome[1] == 1 && syndrome[2] == 0 && syndrome[3] == 0 && syndrome[4] == 1) errors[26*i/31+19] = true;
				else errors[26*i/31+19] = false;
				
				if(syndrome[0] == 1 && syndrome[1] == 1 && syndrome[2] == 0 && syndrome[3] == 1 && syndrome[4] == 0) errors[26*i/31+20] = true;
				else errors[26*i/31+20] = false;
				if(syndrome[0] == 1 && syndrome[1] == 1 && syndrome[2] == 0 && syndrome[3] == 1 && syndrome[4] == 1) errors[26*i/31+21] = true;
				else errors[26*i/31+21] = false;
				if(syndrome[0] == 1 && syndrome[1] == 1 && syndrome[2] == 1 && syndrome[3] == 0 && syndrome[4] == 0) errors[26*i/31+22] = true;
				else errors[26*i/31+22] = false;
				if(syndrome[0] == 1 && syndrome[1] == 1 && syndrome[2] == 1 && syndrome[3] == 0 && syndrome[4] == 1) errors[26*i/31+23] = true;
				else errors[26*i/31+23] = false;
				if(syndrome[0] == 1 && syndrome[1] == 1 && syndrome[2] == 1 && syndrome[3] == 1 && syndrome[4] == 0) errors[26*i/31+24] = true;
				else errors[26*i/31+24] = false;
				if(syndrome[0] == 1 && syndrome[1] == 1 && syndrome[2] == 1 && syndrome[3] == 1 && syndrome[4] == 1) errors[26*i/31+25] = true;
				else errors[26*i/31+25] = false;
			}
			else
			{
				errors[26*i/31] = false;
				errors[26*i/31+1] = false;
				errors[26*i/31+2] = false;
				errors[26*i/31+3] = false;
				errors[26*i/31+4] = false;
				errors[26*i/31+5] = false;
				errors[26*i/31+6] = false;
				errors[26*i/31+7] = false;
				errors[26*i/31+8] = false;
				errors[26*i/31+9] = false;
				
				errors[26*i/31+10] = false;
				errors[26*i/31+11] = false;
				errors[26*i/31+12] = false;
				errors[26*i/31+13] = false;
				errors[26*i/31+14] = false;
				errors[26*i/31+15] = false;
				errors[26*i/31+16] = false;
				errors[26*i/31+17] = false;
				errors[26*i/31+18] = false;
				errors[26*i/31+19] = false;
				
				errors[26*i/31+20] = false;
				errors[26*i/31+21] = false;
				errors[26*i/31+22] = false;
				errors[26*i/31+23] = false;
				errors[26*i/31+24] = false;
				errors[26*i/31+25] = false;
			}
			output[26*i/31] = transferdata[i+2];
			output[26*i/31+1] = transferdata[i+4];
			output[26*i/31+2] = transferdata[i+5];
			output[26*i/31+3] = transferdata[i+6];
			output[26*i/31+4] = transferdata[i+8];
			output[26*i/31+5] = transferdata[i+9];
			output[26*i/31+6] = transferdata[i+10];
			output[26*i/31+7] = transferdata[i+11];
			output[26*i/31+8] = transferdata[i+12];
			output[26*i/31+9] = transferdata[i+13];
			
			output[26*i/31+10] = transferdata[i+14];
			output[26*i/31+11] = transferdata[i+16];
			output[26*i/31+12] = transferdata[i+17];
			output[26*i/31+13] = transferdata[i+18];
			output[26*i/31+14] = transferdata[i+19];
			output[26*i/31+15] = transferdata[i+20];
			output[26*i/31+16] = transferdata[i+21];
			output[26*i/31+17] = transferdata[i+22];
			output[26*i/31+18] = transferdata[i+23];
			output[26*i/31+19] = transferdata[i+24];
			
			output[26*i/31+20] = transferdata[i+25];
			output[26*i/31+21] = transferdata[i+26];
			output[26*i/31+22] = transferdata[i+27];
			output[26*i/31+23] = transferdata[i+28];
			output[26*i/31+24] = transferdata[i+29];
			output[26*i/31+25] = transferdata[i+30];
		}
		count.count(source, output, errors);
		return output;
	}

	public static int[] Hamming_7_4(int[] transferdata) {
		int[] output = new int[4*transferdata.length/7];
		int [] syndrome = new int[3];
		for(int i = 0; i < transferdata.length; i+=7) { // bierzemy 7 bitów, korygujemy i oddajemy 4
			for(int j = 0; j < 3; j++) {
				for (int k = 0; k < 7; k++) {
					syndrome[j] += HammingH_7_4[j][k] * transferdata[i+k];
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
