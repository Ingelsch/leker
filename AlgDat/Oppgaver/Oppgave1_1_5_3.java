package AlgDat.Oppgaver;

public class Oppgave1_1_5_3
{
	  public static int maks(int[] a)  // versjon 3 av maks-metoden
	  {
	    int sist = a.length - 1;       // siste posisjon i tabellen
	    int m = 0;                     // indeks til st�rste verdi
	    int maksverdi = a[0];          // st�rste verdi
	    int temp = a[sist];            // tar vare p� siste verdi
	    a[sist] = maksverdi;          // legger maksverdi sist (vaktpost)

	    for (int i = 0; ; i++)         // i starter med 0, vaktposten gir grenseverdier, s� ingen i < a.length
	      if (a[i] >= maksverdi)       // denne blir sann til slutt
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
	          a[sist]=maksverdi;		// maksverdi legges sist
	        }
	      }
	  } // maks
}
