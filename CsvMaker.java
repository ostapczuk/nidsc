import java.io.*;

public class CsvMaker // TODO pożądany format to: all, corrected, uncorrectable
{
    public static void saveToCsv(int ok, int corrected, int wrong, int uncorrectable, String fileName)
    {
        try (FileWriter fw = new FileWriter(fileName, true))
        {
            String data = ok + "," + corrected + "," + uncorrectable + "," + wrong + "\n";
            fw.write(data);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
    
    public static void saveToCsv(int ok, int corrected, int wrong, String fileName)
    {
        saveToCsv(ok, corrected, wrong, 0, fileName);
    }
    
    public static void saveToCsv(int ok, int corrected, int wrong, int uncorrectable)
    {
        saveToCsv(ok, corrected, wrong, uncorrectable, "results.csv");
    }
    
    public static void saveToCsv(int ok, int corrected, int wrong)
    {
        saveToCsv(ok, corrected, wrong, 0, "results.csv");
    }
    
    public static void columnnames(String filename)
    {
    	try (PrintWriter writer = new PrintWriter(filename))
    	{
	    	writer.print("");
    	}
    	catch (IOException e)
    	{
    		System.out.println(e);
    	}
        try (FileWriter fw = new FileWriter(filename, true))
        {
            String data = "Poprawnych,Poprawionych,Zepsutych,Niewykryte\n";
            fw.write(data);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
}