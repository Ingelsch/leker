package algDat;

public class Eksempel1_1_2
{
	public static int maks(int[] a) // a er en heltallstabell
	{
		if (a.length < 1)
		{
			throw new java.util.NoSuchElementException("a er tom"); 
		}
		
		int m = 0; // indeks til største verdi
		
		for (int i = 1; i < a.length; i++)// obs: starter med i = 1
		{
			if (a[i] >= a[m])		// ">=" viser her siste verdi av største verdi, men ">" viser kun største verdi (pa tidligste posisjon)
			{
				m = i; // indeksen oppdateres
			}
		}
		return m; // returnerer indeksen/posisjonen til største verdi
	} //maks
 

}

