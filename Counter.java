
public class Counter 
{
	public int packets = 0;			//Iloœæ pakietów
	
	public int correct = 0;			//Poprawne
	public int corrected = 0;		//Poprawione
	public int mistakes = 0;		//Iloœæ b³êdów (wykrytych)
	public int broken = 0;			//Zepsute przez program (zle poprawione)
	public int notfound = 0;		//b³êdy nie wykryte
	
	//org - oryginalne dane, 
	//res wynik programu po odkodowaniu, 
	//mis - tablica informuj¹ca czy w miejscu by³ wykryty b³¹d (w danej trójce czy ile bêdziemy tam zwiêkszaæ to)
	//Ta funkcja bêdzie wywo³ywana dla ka¿dego pakietu
	public void count(int[] org, int[] res, boolean[] mis) 
	{
		packets++;
		//true wyst¹pi³ b³¹d, false nie wykryto b³êdu
		for(int i=0; i<mis.length; i++)
		{
			if(mis[i]) //je¿eli znaleziono b³ad
			{
				mistakes++;
				if(org[i] == res[i]) corrected++; //je¿eli wartoœæ siê zgadza to ok
				else broken++;						// W przeciwnym wypadku b³¹d niepoprawialny
			}
			else
			{
				if(org[i] == res[i]) correct++;   //je¿eli nie ma b³edu i wartoœæ siê zgadza to by³o dajziobu
				else notfound++;					//je¿eli nie to tak siê zepsu³o ¿e b³¹d nie zosta³ wykryty
			}
		}
	}
}
