package algDat;

public class Eksempel1_1_5
{
	  public static int maks(int[] a)  // versjon 3 av maks-metoden
	  {
	    int sist = a.length - 1;       // siste posisjon i tabellen
	    int m = 0;                     // indeks til st�rste verdi
	    int maksverdi = a[0];          // st�rste verdi
	    int temp = a[sist];            // tar vare p� siste verdi
	    a[sist] = 0x7fffffff;          // legger tallet 2147483647 sist

	    for (int i = 0; ; i++)         // i starter med 0
	      if (a[i] > maksverdi)       // denne blir sann til slutt
	      {
	        if (i == sist)             // sjekker om vi er ferdige
	        {
	          a[sist] = temp;          // legger siste verdi tilbake
	          return temp >= maksverdi ? sist : m;   // er siste st�rst?
	        }
	        else
	        {
	          maksverdi = a[i];        // maksverdi oppdateres
	          m = i;                   // m oppdateres
	        }
	      }
	  } // maks
}
