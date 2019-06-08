public class Nidsc {
	
	static int repLength = 3; 
	static int packetLength = 21;
	static int loop = 1000;
	static Counter count;
	
  public static void main(String args[]) 
  {
	Nidsc nidsc = new Nidsc();
    System.out.println("NIDSC2 - PROJEKT");
    System.out.println("Jakub Ostapczuk, Adam Krizar, Pawel� Norberciak");
    CsvMaker.columnnames("repetition.csv");
    CsvMaker.columnnames("Hamming_7_4.csv");
    CsvMaker.columnnames("Hamming_15_11.csv");
    CsvMaker.columnnames("Hamming_31_26.csv");
    Data inputdata;
    int[] transferdata;
    int[] outputdata;
    System.out.println("Begin:");
    //System.out.println("TEST RUN: REPETITION:"); ////////////////////////////////
    for(int i = 0; i < loop; i++)
    {
    	System.out.println(i);
    	inputdata = new Data(1144); // liczba podzielna przez 4, 11 i 26 - do kodow Hamminga(7,4), (15,11) i (31,26)
    	count = new Counter();
    	//System.out.println("\nINPUT DATA:");
    
    	/*for(int bit : inputdata.bits) {
    		System.out.print(bit);
    	}*/
    
    	//System.out.println("\nTRANSFER DATA:");
    	transferdata = Encoder.repetition(inputdata.bits, repLength);
    	/*for(int bit : transferdata) {
    		System.out.print(bit);
    	}*/
    
    	transferdata = Noisemaker.randomErrors(transferdata);

    	/*System.out.println("\nCORRUPTED DATA:");
    	for(int bit : transferdata) {
    		System.out.print(bit);
    	}*/
    
    	//System.out.println("\nOUTPUT DATA:");
    
    	outputdata = Decoder.repetition(transferdata,repLength, count, inputdata.bits);
    
    	/*for(int bit : outputdata) {
    		System.out.print(bit);
    	}*/
    
    	//System.out.println("\n\nPoprawnych: " + count.correct + "\nPoprawionych: " + count.corrected + "\nWykrytych bledow: " + count.mistakes + "\nZepsutych: " + count.broken + "\nBledy nie wykryte: " + count.notfound);
    	CsvMaker.saveToCsv(count.correct, count.corrected, count.broken, count.notfound, "repetition.csv");
    
    
    	count = new Counter(); //reset licznika

    	/*System.out.println("\n\nTEST RUN: CRC:"); //////////////////////////////////////////////////////
    	System.out.println("\nINPUT DATA:");
    
    	for(int bit : inputdata.bits) {
    		System.out.print(bit);
    	}*/
    
    	//System.out.println("\nTRANSFER DATA:");
    	transferdata = Encoder.Hamming_7_4(inputdata.bits);
    	/*for(int bit : transferdata) {
    		System.out.print(bit);
    	}*/
    
    	transferdata = Noisemaker.randomErrors(transferdata);

    	/*System.out.println("\nCORRUPTED DATA:");
    	for(int bit : transferdata) {
    		System.out.print(bit);
    	}*/
    
    	// System.out.println("\nOUTPUT DATA:");
    
    
    	outputdata = Decoder.Hamming_7_4(transferdata, count, inputdata.bits);
    	
    	/*for(int bit : outputdata) {
    		System.out.print(bit);
    	}*/    
    	
    	//System.out.println("\n\nPoprawnych: " + count.correct + "\nPoprawionych: " + count.corrected + "\nWykrytych bledow: " + count.mistakes + "\nZepsutych: " + count.broken + "\nBledy nie wykryte: " + count.notfound);
    	CsvMaker.saveToCsv(count.correct, count.corrected, count.broken, count.notfound, "Hamming_7_4.csv");
    	
    	count = new Counter(); //reset licznika
    	
    	transferdata = Encoder.Hamming_15_11(inputdata.bits);
    	
    	transferdata = Noisemaker.randomErrors(transferdata);
    	
    	outputdata = Decoder.Hamming_15_11(transferdata, count, inputdata.bits);
    	
    	CsvMaker.saveToCsv(count.correct, count.corrected, count.broken, count.notfound, "Hamming_15_11.csv");
    	
    	
    	count = new Counter(); //reset licznika
    	
    	transferdata = Encoder.Hamming_31_26(inputdata.bits);
    	
    	transferdata = Noisemaker.randomErrors(transferdata);
    	
    	outputdata = Decoder.Hamming_31_26(transferdata, count, inputdata.bits);
    	
    	CsvMaker.saveToCsv(count.correct, count.corrected, count.broken, count.notfound, "Hamming_31_26.csv");
    }
    System.out.println("Done.");
    
  }
}