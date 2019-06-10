public class Nidsc {
	
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

    	transferdata = Encoder.repetition(inputdata.bits, 3);
    
    	transferdata = Noisemaker.randomErrors(transferdata);
    	
    	outputdata = Decoder.repetition(transferdata, 3, count, inputdata.bits);
    
    	CsvMaker.saveToCsv(count.correct, count.corrected, count.broken, count.notfound, "repetition3.csv");
    
    	
    	count = new Counter();

    	transferdata = Encoder.repetition(inputdata.bits, 5);
    
    	transferdata = Noisemaker.randomErrors(transferdata);
    	
    	outputdata = Decoder.repetition(transferdata, 5, count, inputdata.bits);
    
    	CsvMaker.saveToCsv(count.correct, count.corrected, count.broken, count.notfound, "repetition5.csv");
    	
    	
    	count = new Counter();

    	transferdata = Encoder.repetition(inputdata.bits, 7);
    
    	transferdata = Noisemaker.randomErrors(transferdata);
    	
    	outputdata = Decoder.repetition(transferdata, 7, count, inputdata.bits);
    
    	CsvMaker.saveToCsv(count.correct, count.corrected, count.broken, count.notfound, "repetition7.csv");
    
    
    	count = new Counter(); //reset licznika

    	transferdata = Encoder.Hamming_7_4(inputdata.bits);
    	
    	transferdata = Noisemaker.randomErrors(transferdata);

    	outputdata = Decoder.Hamming_7_4(transferdata, count, inputdata.bits);
    	
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