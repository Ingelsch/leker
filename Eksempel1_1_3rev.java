public class Eksempel1_1_3rev
{
	public static int maks(int[] a) // versjon 2 av maks-metoden
	{
  	int m = 0; // indeks til st�rste verdi
  	
  	int maksverdi = a[0]; // st�rste verdi
  	
  	for (int i = 1; i < a.length; i++) 
  		if (a[i] >= maksverdi) // ">=" viser her siste verdi av st�rste verdi, men ">" viser kun st�rste verdi (pa tidligste posisjon)
    	{
    		maksverdi = a[i]; // st�rste verdi oppdateres
    		m = i; // indeks til st�rste verdi oppdateres
    	}
  	
  	return m; // returnerer indeks/posisjonen til st�rste verdi
	} // maks
 
  public static void main(String[] args)
  {
  	int[] a =	{8, 4, 17, 10, 6, 20, 1, 11, 20, 20, 15, 3, 18, 9, 2, 7, 19};
  	int i = maks(a);
  	System.out.println("Maksverdien ligger pa indeks " + i + ", og har verdi " + a[i]);
  }
}

