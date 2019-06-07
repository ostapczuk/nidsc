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
    CsvMaker.columnnames("Hamming.csv");
    Data inputdata;
    int[] transferdata;
    int[] outputdata;
    System.out.println("Begin:");
    //System.out.println("TEST RUN: REPETITION:"); ////////////////////////////////
    for(int i = 0; i < loop; i++)
    {
    	System.out.println(i);
    	inputdata = new Data(1024); // liczba podzielna przez 4 - do kodu Hamminga(7,4)
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
    	transferdata = Encoder.Hamming74(inputdata.bits);
    	/*for(int bit : transferdata) {
    		System.out.print(bit);
    	}*/
    
    	transferdata = Noisemaker.randomErrors(transferdata);

    	/*System.out.println("\nCORRUPTED DATA:");
    	for(int bit : transferdata) {
    		System.out.print(bit);
    	}*/
    
    	// System.out.println("\nOUTPUT DATA:");
    
    
    	outputdata = Decoder.Hamming74(transferdata, count, inputdata.bits);
    	
    	/*for(int bit : outputdata) {
    		System.out.print(bit);
    	}*/    
    	
    	//System.out.println("\n\nPoprawnych: " + count.correct + "\nPoprawionych: " + count.corrected + "\nWykrytych bledow: " + count.mistakes + "\nZepsutych: " + count.broken + "\nBledy nie wykryte: " + count.notfound);
    	CsvMaker.saveToCsv(count.correct, count.corrected, count.broken, count.notfound, "Hamming.csv");
    }
    System.out.println("Done.");
    
  }
}