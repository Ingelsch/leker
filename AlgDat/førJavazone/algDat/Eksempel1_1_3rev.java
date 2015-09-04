package algDat;

public class Eksempel1_1_3rev
{
	public static int maks(int[] a) // versjon 2 av maks-metoden
	{
  	int m = 0; // indeks til st�rste verdi Operasjoner 1
  	
  	int maksverdi = a[0]; // st�rste verdi Operasjoner 2
  	
  	for (int i = 1; i < a.length; i++) // Operasjoner 1 + n + (n-1)
  		if (a[i] >= maksverdi) // ">=" viser her siste verdi av st�rste verdi, men ">" viser kun st�rste verdi (pa tidligste posisjon) Operasjoner 2(n-1)
    	{
    		maksverdi = a[i]; // st�rste verdi oppdateres Operasjoner 2x
    		m = i; // indeks til st�rste verdi oppdateres Operasjoner x
    	}
  	
  	return m; // returnerer indeks/posisjonen til st�rste verdi Operasjoner 1
	} // maks
 

}

