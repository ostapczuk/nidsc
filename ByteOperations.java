//
// Chwilowo jeszcze nie dzia³a - update wkrótce
//
import java.io.File;
import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.nio.ByteBuffer;

public class ByteOperations 
{
	Scanner scan = new Scanner(System.in);
	Set options = new HashSet();
	int chance = 1000;
	Random generator = new Random();
	
	//Tutaj przechowuje bity które chce zmieniæ
	private ArrayList<byte[]> content = new ArrayList<byte[]>();
	
	//Otwarcie pliku, którego dane chcemy zepsuÄ‡
	public ByteOperations()
	{
		options.add(StandardOpenOption.CREATE);
		options.add(StandardOpenOption.APPEND);
		System.out.println("Podaj nazwe pliku: ");
		String filename = scan.nextLine();
		readfile(filename);
	}
	
	//Funkcja wczytuj¹cca nasz wygenreowany plik
	private void readfile(String filename)
	{
		
		File file = new File(filename);
		try(SeekableByteChannel sbc = Files.newByteChannel(file.toPath()))
		{
			ByteBuffer buf = ByteBuffer.allocate(10);
			while(sbc.read(buf) > 0)
			{
				buf.rewind();
				content.add(buf.array());
				buf.flip();
				buf.clear();
			}
		} 
		catch (IOException error) 
		{
			System.out.println("B³¹d odczytu - readfile");
		}
		//savefile();
		randomErrors();
	}
	
	//Funkcja wprowadzajaca losowe b³êdy do pliku
	private void randomErrors()
	{
		for(byte[] b: content)
		{
			for(byte bb: b)
			{
				if(generator.nextInt(chance) == 1)
				{
					//bb = 0;
				}
			}
		}
		savefile();
	}
	
	//Funckja zapisuj¹ca rezultat
	void savefile()
	{
		File result = new File("rezultat");
		if(result.exists()) result.delete();
		try 
		{
			result.createNewFile();
		} 
		catch (IOException e1) 
		{
			System.out.println("B³¹d, nie mo¿na utworzyæ pliku - savefile");
		}
		try(SeekableByteChannel out = Files.newByteChannel(result.toPath(), options))
		{
			//for(byte[] by: content)
			byte[] by;
			for( int i=0; i<content.size(); i++)
			{
				by = content.get(i);
				out.write(ByteBuffer.wrap(by)); 
			}
		} 
		catch (IOException e) 
		{
			System.out.println("B³¹d zapisywania - savefile");
		}
	}
}
