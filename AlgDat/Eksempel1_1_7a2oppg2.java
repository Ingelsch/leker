package algDat;


public class Eksempel1_1_7a2oppg2
{
	public static int maks(int[] a)  // a er en heltallstabell
	  {
	    if (a.length < 1)
	      throw new java.util.NoSuchElementException("a er tom");

	    int m = 0;  // indeks til største verdi

	    for (int i = 1; i < a.length; i++) // obs: starter med i = 1
	    {
	      if (a[i] > a[m]) m = i;  // indeksen oppdateres
	    }

	    return m;  // returnerer indeksen/posisjonen til største verdi

	  } // maks
	  
	/*Utvid metoden makstest. 
	 * Bruk tabeller der den største er først, er sist og forekommer flere steder. 
	 * Bruk en tabell med kun én verdi, kun to verdier som er ulike og kun to verdier som er like. 
	 * Lag en test for en null-tabell. La også makstest returnere antall feil.*/
	
	public static void makstest()
	  {
	    //int[] a = {10,3,5,7,9,6,8,2,1,4};   // maksverdien 10 er i posisjon 0
	    //int[] a = {8,3,5,7,9,6,10,2,1,4};   // maksverdien 10 er i posisjon 6
	    //int[] a = {8,3,5,7,9,6,4,2,1,10};   // maksverdien 10 er i posisjon 10
	    //int[] a = {5}; 						// kun en verdi
	    int[] a = {8,3}; 					// to ulike
	    //int[] a = {6,6}; 					// to like

	    if (maks(a) != 0)  // kaller maks-metoden
	      System.out.println("Kodefeil: Gir feil posisjon for maksverdien!");

	    a = new int[0];  // en tom tabell, lengde lik 0
	    boolean unntak = false;

	    try
	    {
	      maks(a);  // kaller maks-metoden
	    }
	    catch (Exception e1)
	    {
	      unntak = true;
	      if (!(e1 instanceof java.util.NoSuchElementException))
	        System.out.println("Kodefeil: Feil unntak for en tom tabell!");
	    }

	    if (!unntak)
	      System.out.println("Kodefeil: Mangler unntak for en tom tabell!");
	  }
}
