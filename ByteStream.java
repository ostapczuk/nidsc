import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ByteStream
{
    public byte[] byteArray;
    
    public ByteStream()
    {
        byteArray = null;
    }
    public ByteStream(String fileName)
    {
        byteArray = null;
        readFromFile(fileName);
    }
    
    public readFromFile(String fileName) throws IOException
    {
        try
        {
            Path pathToFile = Paths.get(fileName);
            byteArray = Files.readAllBytes(pathToFile);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
    
    public saveToFile(String fileName) throws IOException
    {
        try (FileOutputStream fos = new FileOutputStream(fileName))
        {
            fos.write(byteArray);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
}