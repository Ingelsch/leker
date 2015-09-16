package algDat;

public class Eksempel1_1_3rev
{
	public static int maks(int[] a) // versjon 2 av maks-metoden
	{
  	int m = 0; // indeks til største verdi Operasjoner 1
  	
  	int maksverdi = a[0]; // største verdi Operasjoner 2
  	
  	for (int i = 1; i < a.length; i++) // Operasjoner 1 + n + (n-1)
  		if (a[i] >= maksverdi) // ">=" viser her siste verdi av største verdi, men ">" viser kun største verdi (pa tidligste posisjon) Operasjoner 2(n-1)
    	{
    		maksverdi = a[i]; // største verdi oppdateres Operasjoner 2x
    		m = i; // indeks til største verdi oppdateres Operasjoner x
    	}
  	
  	return m; // returnerer indeks/posisjonen til største verdi Operasjoner 1
	} // maks
 

}

