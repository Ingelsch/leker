package AlgDat;

import java.util.OptionalInt;

public class Eksempel1_1_7b
{
	  public static OptionalInt maks(int[] a)           // indeks til største verdi
	  {
	    if (a.length < 1) return OptionalInt.empty();   // en konstruksjonsmetode

	    int m = 0, maksverdi = a[0];                    // startindeks og verdi

	    for (int i = 1; i < a.length; i++)              // starter med i = 1
	    {
	      if (a[i] > maksverdi)
	      {
	        m = i; maksverdi = a[i];                    // oppdaterer
	      }
	    }

	    return OptionalInt.of(m);                       // en konstruksjonsmetode
	  }

}
