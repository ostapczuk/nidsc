public class Counter 
{
	public int packets = 0;			//Ilo�� pakiet�w
	
	public int correct = 0;			//Poprawne
	public int corrected = 0;		//Poprawione
	public int mistakes = 0;		//Ilo�� b��d�w (wykrytych)
	public int broken = 0;			//Zepsute przez program (zle poprawione)
	public int notfound = 0;		//b��dy nie wykryte
	
	//org - oryginalne dane, 
	//res wynik programu po odkodowaniu, 
	//mis - tablica informuj�ca czy w miejscu by� wykryty b��d (w danej tr�jce czy ile b�dziemy tam zwi�ksza� to)
	//Ta funkcja b�dzie wywo�ywana dla ka�dego pakietu
	public void count(int[] org, int[] res, boolean[] mis) 
	{
		packets++;
		//true wyst�pi� b��d, false nie wykryto b��du
		for(int i=0; i<mis.length; i++)
		{
			if(mis[i]) //je�eli znaleziono b�ad
			{
				mistakes++;
				if(org[i] == res[i]) corrected++; //je�eli warto�� si� zgadza to ok
				else broken++;						// W przeciwnym wypadku b��d niepoprawialny
			}
			else
			{
				if(org[i] == res[i]) correct++;   //je�eli nie ma b�edu i warto�� si� zgadza to by�o dajziobu
				else notfound++;					//je�eli nie to tak si� zepsu�o �e b��d nie zosta� wykryty
			}
		}
	}
}
